package main.gameobject;

import main.Vector2D;
import main.gameobject.bullet.Bullet;
import main.gameobject.bullet.PlayerBullet;
import main.observer.Event;
import main.strategy.movement.EnemyPatrolMovement;
import main.util.Point;
import main.worldStateManagement.WorldMediator;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class EnemyShip extends GameObject {
    private final Polygon shape;
    private double aliveTime = 0;
    private double lastShotTime = 0;
    private static final double FIRE_RATE = 3;
    private double angle = 0;

    private double patrolPhaseTime = 0;
    private boolean patrolVerticalLeg = false;

    private final Color primaryColor = new Color(20, 20, 25);
    private final Color accentColor = new Color(255, 0, 50);

    public EnemyShip(Point position, Vector2D velocity) {
        this.position = position;
        this.velocity = velocity;

        int[] xPoints = {0, 15, 25, 15, 0, -15, -25, -15};
        int[] yPoints = {-20, -5, 10, 5, 15, 5, 10, -5};
        this.shape = new Polygon(xPoints, yPoints, 8);

        this.movementStrategy = new EnemyPatrolMovement();
    }

    public double getPatrolPhaseTime() {
        return patrolPhaseTime;
    }

    public void setPatrolPhaseTime(double patrolPhaseTime) {
        this.patrolPhaseTime = patrolPhaseTime;
    }

    public boolean isPatrolVerticalLeg() {
        return patrolVerticalLeg;
    }

    public void setPatrolVerticalLeg(boolean patrolVerticalLeg) {
        this.patrolVerticalLeg = patrolVerticalLeg;
    }

    @Override
    public void update(double deltaTime, WorldMediator world) {
        aliveTime += deltaTime;
        lastShotTime += deltaTime;

        super.update(deltaTime, world);
        Player player = world.getPlayer();
        if (player != null && !player.isDead()) {
            double dx = player.getPosition().getX() - position.getX();
            double dy = player.getPosition().getY() - position.getY();
            this.angle = Math.atan2(dy, dx) + Math.toRadians(90);
            if (lastShotTime >= FIRE_RATE) {
                notifyObservers(Event.ENEMY_SHOT_FIRED);
                lastShotTime = 0;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform old = g.getTransform();
        g.translate(position.getX(), position.getY());
        g.rotate(angle);

        g.setColor(primaryColor);
        g.fill(shape);

        float pulse = (float) (0.7 + 0.3 * Math.sin(aliveTime * 8.0));
        g.setColor(new Color(accentColor.getRed(), accentColor.getGreen(), accentColor.getBlue(), (int)(255 * pulse)));

        g.setStroke(new BasicStroke(2.5f));
        g.draw(shape);

        g.setColor(Color.WHITE);
        g.fillOval(-2, -12, 4, 4);

        g.setTransform(old);
    }

    @Override
    public Shape getBounds() {
        AffineTransform at = new AffineTransform();
        at.translate(position.getX(), position.getY());
        at.rotate(angle);
        return at.createTransformedShape(shape);
    }

    public double getAngle() {
        return angle;
    }

    @Override
    public void onCollision(GameObject other) {
        if (other instanceof PlayerBullet) {
            destroy();
        }
    }
}