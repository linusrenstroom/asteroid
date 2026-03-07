package main.gameobject;

import main.Vector2D;
import main.conf.GameConfig;
import main.util.Point;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends GameObject {
    private final Polygon shipBoundingBox;
    private final double rotationSpeed = GameConfig.PLAYER_ROTATION_SPEED;
    private final double acceleration = GameConfig.PLAYER_ACCELERATION;
    private final double dragPerSecond = GameConfig.PLAYER_DRAG_PER_SECOND;
    private final double headingOffset = GameConfig.PLAYER_HEADING_OFFSET_RADIANS;
    private final double bulletSpeed = GameConfig.PLAYER_BULLET_SPEED;
    private final double maxShootCooldown = GameConfig.PLAYER_SHOOT_COOLDOWN;

    private final int[] shipXPoints = GameConfig.PLAYER_SHIP_X_POINTS;
    private final int[] shipYPoints = GameConfig.PLAYER_SHIP_Y_POINTS;
    private final int shipPointCount = GameConfig.PLAYER_SHIP_POINT_COUNT;

    private double angle;
    private boolean move;
    private boolean rotateLeft;
    private boolean rotateRight;
    private double currentShootCooldown = 0;

    public Player(Point startPosition) {
        this.position = new Point(startPosition.getX(), startPosition.getY());
        this.velocity = new Vector2D(0, 0);
        this.shipBoundingBox = new Polygon(shipXPoints, shipYPoints, shipPointCount);
        this.angle = 0;
    }

    @Override
    public void update(double deltaTime) {
        int rotationInput = (rotateRight ? 1 : 0) - (rotateLeft ? 1 : 0);
        angle += rotationInput * rotationSpeed * deltaTime;

        double heading = angle - headingOffset;

        if (move) {
            velocity = velocity.add(new Vector2D(
                    Math.cos(heading) * acceleration * deltaTime,
                    Math.sin(heading) * acceleration * deltaTime
            ));
        }

        if (currentShootCooldown > 0) {
            currentShootCooldown -= deltaTime;
        }

        velocity = velocity.multiply(Math.pow(dragPerSecond, deltaTime));

        position.setX(position.getX() + velocity.x * deltaTime);
        position.setY(position.getY() + velocity.y * deltaTime);

        moveOutsideScreenX();
        moveOutsideScreenY();
    }

    @Override
    public void draw(Graphics2D g) {
        g.translate((int) position.getX(), (int) position.getY());
        g.rotate(angle);
        g.setColor(Color.WHITE);
        g.fill(shipBoundingBox);
        g.draw(shipBoundingBox);
        g.rotate(-angle);
        g.translate(-position.getX(), -position.getY());
    }
    @Override
    public Shape getBounds() {
        AffineTransform at = new AffineTransform();
        at.translate(position.getX(), position.getY());
        at.rotate(angle);
        return at.createTransformedShape(shipBoundingBox);
    }

    public void shoot() {
        if (currentShootCooldown <= 0) {
            double heading = angle - headingOffset;
            Vector2D bulletVel = new Vector2D(
                    Math.cos(heading) * bulletSpeed,
                    Math.sin(heading) * bulletSpeed
            );

            currentShootCooldown = maxShootCooldown;
        }
    }

    public void setMove(boolean on) { move = on; }
    public void setRotateLeft(boolean on) { rotateLeft = on; }
    public void setRotateRight(boolean on) { rotateRight = on; }

    public void stop() {
        velocity = new Vector2D(0, 0);
    }

    private void moveOutsideScreenX() {
        if (position.getX() < 0) {
            position.setX(GameConfig.SCREEN_WIDTH);
        } else if (position.getX() > GameConfig.SCREEN_WIDTH) {
            position.setX(0);
        }
    }

    private void moveOutsideScreenY() {
        if (position.getY() < 0) {
            position.setY(GameConfig.SCREEN_HEIGHT);
        } else if (position.getY() > GameConfig.SCREEN_HEIGHT) {
            position.setY(0);
        }
    }

}