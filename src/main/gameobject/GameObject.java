package main.gameobject;

import main.Vector2D;
import main.observer.Observable;
import main.strategy.movement.MovementStrategy;
import main.util.Point;
import main.worldStateManagement.WorldMediator;
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

    public void update(double deltaTime, WorldMediator world) {
        update(deltaTime);
    }

    public boolean isDead() { return dead; }

    public void destroy() { this.dead = true; }

    public abstract void draw(Graphics2D g);

    public void onCollision(GameObject other) {}

    public abstract Shape getBounds();

    public boolean collidesWith(GameObject other) {

        Shape s1 = this.getBounds();
        Shape s2 = other.getBounds();

        if (s1 == null || s2 == null) {
            return false;
        }

        Area a = new Area(s1);
        a.intersect(new Area(s2));

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
