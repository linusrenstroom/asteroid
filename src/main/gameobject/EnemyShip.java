package main.gameobject;

import main.Vector2D;
import main.conf.GameConfig;
import main.util.Point;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;

public class EnemyShip extends GameObject {
    private final Polygon shape;
    private final int radius = 15;
    private double aliveTime = 0;
    private double lastShotTime = 0;
    private static final double FIRE_RATE = 2.0;

    public EnemyShip(Point position, Vector2D velocity) {
        this.position = position;
        this.velocity = velocity;

        int[] xPoints = {-20, -10, -5, 5, 10, 20, 10, -10};
        int[] yPoints = {0, -5, -10, -10, -5, 0, 5, 5};
        this.shape = new Polygon(xPoints, yPoints, 8);
    }
    public void update(double deltaTime, List<GameObject> worldObjects) {
        aliveTime += deltaTime;
        lastShotTime += deltaTime;

        position.setX(position.getX() + velocity.x * deltaTime);
        double hover = Math.sin(aliveTime * 4.0) * 2.0;
        position.setY(position.getY() + (velocity.y + hover) * deltaTime);

        if (lastShotTime >= FIRE_RATE) {
            shoot(worldObjects);
            lastShotTime = 0;
        }

        handleScreenWrapping();
    }

    private void handleScreenWrapping() {
    }

    private void shoot(List<GameObject> worldObjects) {
        double angle = Math.random() * 2 * Math.PI;
        Vector2D bulletVel = new Vector2D(Math.cos(angle), Math.sin(angle)).multiply(300.0);
//        worldObjects.add();
    }

    @Override
    public void update(double deltaTime) {
        this.update(deltaTime, null);
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform old = g.getTransform();
        g.translate(position.getX(), position.getY());

        g.setColor(Color.BLACK);
        g.fill(shape);
        g.setColor(Color.GREEN);
        g.setStroke(new BasicStroke(2));
        g.draw(shape);
        if ((int)(aliveTime * 5) % 2 == 0) {
            g.setColor(Color.RED);
            g.fillOval(-3, -7, 6, 4);
        }

        g.setTransform(old);
    }

    @Override
    public Shape getBounds() {
        AffineTransform at = new AffineTransform();
        at.translate(position.getX(), position.getY());
        return at.createTransformedShape(shape);
    }
}