package main.gameobject.bullet;

import main.Vector2D;
import main.conf.GameConfig;
import main.gameobject.GameObject;
import main.strategy.movement.LinearMovement;
import main.strategy.movement.decorator.DespawningMovementStrategy;
import main.util.Point;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public abstract class Bullet extends GameObject {

    private final int radius = GameConfig.BULLET_RADIUS;
    private Color color = Color.WHITE;

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
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.translate(position.getX(), position.getY());
        double angle = Math.atan2(velocity.y, velocity.x);
        g2.rotate(angle);
        int length = 15;
        int thickness = 3;
        g2.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100));
        g2.setStroke(new BasicStroke(thickness + 2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawLine(-length, 0, 0, 0);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(thickness - 1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawLine(-length, 0, 0, 0);

        g2.dispose();
    }

    @Override
    public Shape getBounds() {
        return new Ellipse2D.Double(position.getX() - radius, position.getY() - radius, radius * 2, radius * 2);
    }

    public void setColor(Color color) {
        this.color = color;
    }
}