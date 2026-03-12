package main.gameobject;

import main.Vector2D;
import main.conf.GameConfig;
import main.observer.Observable;
import main.observer.Observer;
import main.strategy.movement.LinearMovement;
import main.strategy.movement.decorator.DespawningMovementStrategy;
import main.util.Point;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Bullet extends GameObject {

    private final int radius = GameConfig.BULLET_RADIUS;
    private final double baseSpeed = GameConfig.BULLET_SPEED;
    public Bullet(Point startPos, Vector2D direction) {
        this.position = new Point(startPos.getX(), startPos.getY());
        this.velocity = direction;
        this.movementStrategy = new DespawningMovementStrategy(
                new LinearMovement(),
               0
        );
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillOval((int)(position.getX() - radius), (int)(position.getY() - radius), radius * 2, radius * 2);
    }

    @Override
    public Shape getBounds() {
        return new Ellipse2D.Double(position.getX() - radius, position.getY() - radius, radius * 2, radius * 2);
    }

    public double getRadius() {
        return radius;
    }


}