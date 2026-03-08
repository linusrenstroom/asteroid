package main.gameobject;

import main.Vector2D;
import main.conf.GameConfig;
import main.observer.Observable;
import main.observer.Observer;
import main.util.Point;


import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class Asteroid extends GameObject implements Observable {
    List<Observer> observerList = new ArrayList<>();
    private int radius;

    public Asteroid() {
        this.radius = GameConfig.ASTEROID_DEFAULT_RADIUS;
        this.position = new Point(GameConfig.ASTEROID_DEFAULT_START_X, GameConfig.ASTEROID_DEFAULT_START_Y);
        this.velocity = new Vector2D(GameConfig.ASTEROID_DEFAULT_VELOCITY_X, GameConfig.ASTEROID_DEFAULT_VELOCITY_Y);
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

        if (position.getX() < -radius) {
            position.setX(GameConfig.SCREEN_WIDTH + radius);
        } else if (position.getX() > GameConfig.SCREEN_WIDTH + radius) {
            position.setX(-radius);
        }

        // Screen wrap Y
        if (position.getY() < -radius) {
            position.setY(GameConfig.SCREEN_HEIGHT + radius);
        } else if (position.getY() > GameConfig.SCREEN_HEIGHT + radius) {
            position.setY(-radius);
        }

    }
    @Override
    public void onCollision(GameObject other) {
        if (other instanceof Bullet) {
            this.destroy();
            notifyObservers();
            other.destroy();
        }
        if(other instanceof Player) {
            this.destroy();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.GRAY);

        g.fillOval(
                (int)(position.getX() - radius),
                (int)(position.getY() - radius),
                radius * 2,
                radius * 2
        );
    }

    @Override
    public Shape getBounds() {
        return new Ellipse2D.Double(
                position.getX() - radius,
                position.getY() - radius,
                radius * 2,
                radius * 2
        );
    }

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
            for (Observer observer : observerList) {
                observer.update(this);
            }
    }
}
