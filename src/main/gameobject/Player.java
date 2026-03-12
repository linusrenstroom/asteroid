package main.gameobject;

import main.Vector2D;
import main.conf.GameConfig;
import main.observer.Event;
import main.strategy.movement.PlayerMovement;
import main.strategy.movement.decorator.WrappingMovementStrategy;
import main.util.Point;
import main.gameobject.asteroids.Asteroid;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends GameObject {
    private final Polygon shipBoundingBox;
    private int lives = 3;
    private double angle;
    private boolean move;
    private boolean rotateLeft;
    private boolean rotateRight;

    private double currentShootCooldown = 0;
    private final double maxShootCooldown = 0.25;
    private final double headingOffset = Math.toRadians(90);

    public Player(Point startPosition) {
        this.position = new Point(startPosition.getX(), startPosition.getY());
        this.velocity = new Vector2D(0, 0);
        this.shipBoundingBox = new Polygon(
                GameConfig.PLAYER_SHIP_X_POINTS,
                GameConfig.PLAYER_SHIP_Y_POINTS,
                GameConfig.PLAYER_SHIP_POINT_COUNT
        );
        this.angle = 0;
        this.movementStrategy = new WrappingMovementStrategy(new PlayerMovement(), 0);
    }

    @Override
    public void update(double deltaTime) {
        double rotationSpeed = 4.0;
        if (rotateLeft) angle -= rotationSpeed * deltaTime;
        if (rotateRight) angle += rotationSpeed * deltaTime;
        super.update(deltaTime);
        if (currentShootCooldown > 0) currentShootCooldown -= deltaTime;
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform old = g.getTransform();
        g.translate(position.getX(), position.getY());
        g.rotate(angle);

        if (move) {
            int[] flameX = {-4, 0, 4};
            int[] flameY = {12, 18 + (int) (Math.random() * 6), 12};
            g.setColor(Color.BLUE);
            g.fillPolygon(flameX, flameY, 3);
        }

        g.setColor(Color.ORANGE);
        g.fill(shipBoundingBox);
        g.draw(shipBoundingBox);
        g.setTransform(old);
    }

    @Override
    public Shape getBounds() {
        AffineTransform at = new AffineTransform();
        at.translate(position.getX(), position.getY());
        at.rotate(angle);
        return at.createTransformedShape(shipBoundingBox);
    }

    @Override
    public void onCollision(GameObject other) {
        if (other instanceof Asteroid) {
            lives--;
            notifyObservers(Event.LOSE_LIFE);
            if (lives <= 0){
                destroy();
                notifyObservers(Event.PLAYER_DIED);
            }
        }
    }

    public void shoot() {
        if (canShoot()) {
            currentShootCooldown = maxShootCooldown;
            notifyObservers(Event.SHOT_FIRED);
        }
    }

    public double getHeadingAngle() {
        return angle - headingOffset;
    }

    public void reset() {
        position.setX(GameConfig.SCREEN_WIDTH / 2.0);
        position.setY(GameConfig.SCREEN_HEIGHT / 2.0);
        velocity = new Vector2D(0, 0);
        lives = 3;
        dead = false;
        notifyObservers();
    }

    private boolean canShoot()          { return currentShootCooldown <= 0; }
    public void setMove(boolean on)     { move = on; }
    public void setRotateLeft(boolean on)  { rotateLeft = on; }
    public void setRotateRight(boolean on) { rotateRight = on; }
    public int getLives()               { return lives; }
    public double getAngle()            { return angle; }
    public void setAngle(double angle)  { this.angle = angle; }
    public boolean isMoving()           { return move; }
    public boolean isRotatingLeft()     { return rotateLeft; }
    public boolean isRotatingRight()    { return rotateRight; }

}