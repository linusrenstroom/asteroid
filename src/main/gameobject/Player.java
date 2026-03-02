package main.gameobject;

import main.Vector2D;
import main.util.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends GameObject{
    private double angle;
    private List<Bullet> bullets;
    private boolean move;

    public Player(Point startPosition){
        this.position = new Point(startPosition.getX(), startPosition.getY());
        this.velocity = new Vector2D(0,0);
        this.angle = 0;
        this.bullets = new ArrayList<>();
    }

    @Override
    public void update(double deltaTime) {

        if (move) {
            double acceleration = 200;
            velocity = velocity.add(new Vector2D(
                    Math.cos(angle) * acceleration * deltaTime,
                    Math.sin(angle) * acceleration * deltaTime
            ));
        }

        position.setX(position.getX() + velocity.x * deltaTime);
        position.setY(position.getY() + velocity.y * deltaTime);

        for (Bullet b : bullets) {
            b.update(deltaTime);
        }

    }

    @Override
    public void draw(Graphics2D g) {
        int[] xPoints = {0, -10, 10}; // tip, left, right
        int[] yPoints = {-15, 10, 10}; // tip is above, base below

        Polygon ship = new Polygon(xPoints, yPoints, 3);

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

    public void rotate(double deltaAngle){
        angle += deltaAngle;
    }

    public void shoot() {
        double bulletSpeed = 300;
        Vector2D bulletVel = new Vector2D(
                Math.cos(angle) * bulletSpeed,
                Math.sin(angle) * bulletSpeed
        );
        bullets.add(new Bullet(position, bulletVel));
    }
}
