package main.gameobject;

import main.Vector2D;
import main.util.Point;

import java.awt.*;
import java.util.Timer;

public class Asteroid extends GameObject {
    private final int radius;

    public Asteroid() {
        radius = 10;
        this.velocity = new Vector2D(10, 0);
        position = new Point(10,10);
    }

    @Override
    public void update(double deltaTime) {
       position.setX(position.getX()+ deltaTime* velocity.x);
       position.setY(position.getY()+ deltaTime* velocity.y);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawOval((int) position.getX(), (int) position.getY(), radius, radius);
    }
}
