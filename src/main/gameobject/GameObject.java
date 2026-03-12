package main.gameobject;

import main.Vector2D;
import main.observer.Observable;
import main.strategy.movement.MovementStrategy;
import main.util.Point;
import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.geom.Area;

public abstract class GameObject extends Observable {

    protected Point position;
    protected Vector2D velocity;
    protected MovementStrategy movementStrategy;

    protected boolean dead = false;

    public void update(double deltaTime){
        if(movementStrategy != null){
            movementStrategy.move(this, deltaTime);
        }
    }

    public boolean isDead() { return dead; }

    public void destroy() { this.dead = true; }

    public abstract void draw(Graphics2D g);

    public void onCollision(GameObject other) {
        System.out.println("Collision Detected: " + other.getClass().getName() + " " + this.getClass().getName());
    }

    public abstract Shape getBounds();

    public boolean collidesWith(GameObject other) {
        Area a = new Area(this.getBounds());
        a.intersect(new Area(other.getBounds()));
        return !a.isEmpty();
    }

    public Vector2D getVelocity() {
        return velocity;
    }
    public Point getPosition() {
        return position;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }


}
