package main.gameobject;

import main.Vector2D;
import main.util.Point;
import main.worldStateManagement.GameObjectContainer;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends GameObject {

    private double angle;
    private Vector2D velocity;
    private final GameObjectContainer world;

    public Player(Point startPosition, GameObjectContainer world){
        this.position = new Point(startPosition.getX(), startPosition.getY());
        this.velocity = new Vector2D(0,0);
        this.angle = 0;
        this.world = world;
    }

    @Override
    public void update(double deltaTime) {
        position.setX(position.getX() + velocity.x * deltaTime);
        position.setY(position.getY() + velocity.y * deltaTime);
    }

    @Override
    public void draw(Graphics2D g) {
        int shipWidth = 20;
        int shipHeight = 25;

        int[] xPoints = {0, -shipWidth / 2, shipWidth / 2};   // tip, left, right
        int[] yPoints = {-shipHeight / 2, shipHeight / 2, shipHeight / 2}; // tip above, base below
        Polygon ship = new Polygon(xPoints, yPoints, 3);

        // Save the original transform
        AffineTransform old = g.getTransform();

        // Translate & rotate only for the ship
        g.translate(position.getX(), position.getY());
        g.rotate(angle - Math.PI/2);
        g.draw(ship);

        // Restore the original transform so the world stays put
        g.setTransform(old);
    }

    public void accelerate(double deltaTime) {
        double acceleration = 200;
        velocity = velocity.add(new Vector2D(
                Math.cos(angle) * acceleration * deltaTime,
                Math.sin(angle) * acceleration * deltaTime
        ));
    }

    public void rotate(double deltaAngle) {
        angle += deltaAngle;
    }

    public void shoot() {
        double bulletSpeed = 300;

        // Ship tip offset relative to the ship's center
        double tipOffset = -12.5; // shipHeight / 2, matches your triangle tip
        double tipX = position.getX() + Math.cos(angle) * tipOffset;
        double tipY = position.getY() + Math.sin(angle) * tipOffset;

        Vector2D bulletVel = new Vector2D(
                Math.cos(angle) * bulletSpeed,
                Math.sin(angle) * bulletSpeed
        );

        world.addObject(new Bullet(tipX, tipY, bulletVel));
    }

    public void stopAcceleration() {
        // you could gradually reduce velocity or just set it to zero if needed
        velocity = new Vector2D(0,0);
    }
}