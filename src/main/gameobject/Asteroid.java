package main.gameobject;

import main.Vector2D;
import main.conf.GameConfig;
import main.util.Point;

import java.awt.*;
import java.util.Timer;

public class Asteroid extends GameObject {
    private int radius;

    public Asteroid() {
        this.radius = 20;
        this.position = new Point(0,0);
        this.velocity = new Vector2D(10,0);
    }

    public Asteroid(int radius, double posX, double posY, double velX, double velY) {
        this.radius = radius;
        this.position = new Point(posX, posY);
        this.velocity = new Vector2D(velX, velY);
    }

    @Override
    public void update(double deltaTime) {
       position.setX(position.getX()+ deltaTime* velocity.x);
       position.setY(position.getY()+ deltaTime* velocity.y);

       // Plus minus 100 för att asteroiden inte ska försvinna innan hela är utanför :D
        if (position.getX() < -100 ||
                position.getX() > GameConfig.SCREEN_WIDTH + 100 ||
                position.getY() < -100 ||
                position.getY() > GameConfig.SCREEN_HEIGHT + 100) {
            this.destroy();
        }

    }

    @Override
    public void draw(Graphics g) {
        g.fillOval((int) position.getX(), (int) position.getY(), radius, radius);
    }
}
