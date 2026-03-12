package main.gameobject.asteroids;

import main.Vector2D;
import main.conf.GameConfig;
import main.gameobject.Bullet;
import main.gameobject.GameObject;
import main.gameobject.Player;
import main.observer.Event;
import main.util.Point;
import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class Asteroid extends GameObject {
    protected final Polygon shape;
    protected final int radius;
    protected double rotationAngle = 0;
    protected double rotationSpeed;

    public Asteroid(Polygon shape, int radius, Point position, Vector2D velocity, double rotationSpeed) {
        this.shape = shape;
        this.radius = radius;
        this.position = position;
        this.velocity = velocity;
        this.rotationSpeed = rotationSpeed;
    }

    @Override
    public void update(double deltaTime) {
        position.setX(position.getX() + velocity.x * deltaTime);
        position.setY(position.getY() + velocity.y * deltaTime);
        rotationAngle += rotationSpeed * deltaTime;
        handleScreenWrapping();
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform old = g.getTransform();
        g.translate(position.getX(), position.getY());
        g.rotate(rotationAngle);

        g.setColor(Color.BLACK);
        g.fill(shape);
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(2));
        g.draw(shape);

        g.setTransform(old);
    }

    @Override
    public Shape getBounds() {
        AffineTransform at = new AffineTransform();
        at.translate(position.getX(), position.getY());
        at.rotate(rotationAngle);
        return at.createTransformedShape(shape);
    }

    private void handleScreenWrapping() {
        if (position.getX() < -radius) position.setX(GameConfig.SCREEN_WIDTH + radius);
        else if (position.getX() > GameConfig.SCREEN_WIDTH + radius) position.setX(-radius);
        if (position.getY() < -radius) position.setY(GameConfig.SCREEN_HEIGHT + radius);
        else if (position.getY() > GameConfig.SCREEN_HEIGHT + radius) position.setY(-radius);
    }

    @Override
    public void onCollision(GameObject other) {
        if (other instanceof Bullet) {
            this.destroy();
            other.destroy();
            notifyObservers(Event.ASTEROID_DESTROYED);
        }
        if(other instanceof Player){
            this.destroy();
        }
    }
}