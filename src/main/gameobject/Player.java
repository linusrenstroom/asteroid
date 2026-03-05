package main.gameobject;

import main.Vector2D;
import main.conf.GameConfig;
import main.util.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends GameObject{
    private double angle;
    private List<Bullet> bullets;
    private boolean move;
    private boolean rotateLeft;
    private boolean rotateRight;

    public Player(Point startPosition){
        this.position = new Point(startPosition.getX(), startPosition.getY());
        this.velocity = new Vector2D(0,0);
        this.angle = 0;
        this.bullets = new ArrayList<>();
    }

    @Override
    public void update(double deltaTime) {
        int rotationInput = (rotateRight ? 1 : 0) - (rotateLeft ? 1 : 0);
        angle += rotationInput * GameConfig.PLAYER_ROTATION_SPEED * deltaTime;
        double heading = angle - GameConfig.PLAYER_HEADING_OFFSET_RADIANS;

        if (move) {
            velocity = velocity.add(new Vector2D(
                    Math.cos(heading) * GameConfig.PLAYER_ACCELERATION * deltaTime,
                    Math.sin(heading) * GameConfig.PLAYER_ACCELERATION * deltaTime
            ));
        }

        velocity = velocity.multiply(Math.pow(GameConfig.PLAYER_DRAG_PER_SECOND, deltaTime));

        position.setX(position.getX() + velocity.x * deltaTime);
        position.setY(position.getY() + velocity.y * deltaTime);

        for (Bullet b : bullets) {
            b.update(deltaTime);
        }

    }

    @Override
    public void draw(Graphics2D g) {
        Polygon ship = new Polygon(
                GameConfig.PLAYER_SHIP_X_POINTS,
                GameConfig.PLAYER_SHIP_Y_POINTS,
                GameConfig.PLAYER_SHIP_POINT_COUNT
        );

        g.translate((int)position.getX(), (int)position.getY());
        g.rotate(angle);

        g.draw(ship);

        // Reset transform so bullets and other objects are drawn correctly
        g.rotate(-angle);
        g.translate(-position.getX(), -position.getY());

        for (Bullet b : bullets) {
            b.draw(g);
        }
    }

    public void setMove(boolean on){
        move = on;
    }

    public void setRotateLeft(boolean on) {
        rotateLeft = on;
    }

    public void setRotateRight(boolean on) {
        rotateRight = on;
    }

    public void rotate(double deltaAngle){
        angle += deltaAngle;
    }

    public void shoot() {
        double heading = angle - GameConfig.PLAYER_HEADING_OFFSET_RADIANS;
        Vector2D bulletVel = new Vector2D(
                Math.cos(heading) * GameConfig.PLAYER_BULLET_SPEED,
                Math.sin(heading) * GameConfig.PLAYER_BULLET_SPEED
        );
        bullets.add(new Bullet(position, bulletVel));
    }

    public void stop() {
        velocity = new Vector2D(0, 0);
    }
}
