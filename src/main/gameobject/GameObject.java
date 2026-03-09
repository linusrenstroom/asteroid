package main.gameobject;

import main.Vector2D;

import java.awt.*;
import java.awt.geom.Area;

import main.util.Point;

public abstract class GameObject {

    protected Point position;
    protected Vector2D velocity;
    public abstract void update(double deltaTime);
    protected boolean dead = false;

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



}
