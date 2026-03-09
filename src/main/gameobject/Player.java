package main.gameobject;

import main.Vector2D;
import main.conf.GameConfig;
import main.observer.Observer;
import main.observer.Observable;
import main.strategy.movement.PlayerMovement;
import main.strategy.movement.decorator.WrappingMovementStrategy;
import main.util.Point;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Player extends GameObject implements Observable {
    private final Polygon shipBoundingBox;
    private int lives = 3;
    private double angle;
    private boolean move;
    private boolean rotateLeft;
    private boolean rotateRight;
    private double currentShootCooldown = 0;
    private final List<Observer> observers = new ArrayList<>();

    public Player(Point startPosition) {
        this.position = new Point(startPosition.getX(), startPosition.getY());
        this.velocity = new Vector2D(0, 0);
        this.shipBoundingBox = new Polygon(
                GameConfig.PLAYER_SHIP_X_POINTS,
                GameConfig.PLAYER_SHIP_Y_POINTS,
                GameConfig.PLAYER_SHIP_POINT_COUNT
        );
        this.angle = 0;
        this.movementStrategy = new WrappingMovementStrategy(new PlayerMovement(), 0);
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        if (currentShootCooldown > 0) currentShootCooldown -= deltaTime;
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform old = g.getTransform();
        g.translate(position.getX(), position.getY());
        g.rotate(angle);
        if (move) {
            int[] flameX = {-4, 0, 4};
            int[] flameY = {12, 18 + (int)(Math.random() * 6), 12};
            g.setColor(Color.BLUE);
            g.fillPolygon(flameX, flameY, 3);
        }
        g.setColor(Color.ORANGE);
        g.fill(shipBoundingBox);
        g.draw(shipBoundingBox);
        g.setTransform(old);

    }

    @Override
    public Shape getBounds() {
        AffineTransform at = new AffineTransform();
        at.translate(position.getX(), position.getY());
        at.rotate(angle);
        return at.createTransformedShape(shipBoundingBox);
    }

    @Override
    public void onCollision(GameObject other) {
        if (other instanceof Asteroid) {
            System.out.println("Asteroid");
            lives--;
            notifyObservers();
            if (lives <= 0) {
                destroy();
            }
        }
    }
    public GameObject shoot() {
        if (!canShoot()) return null;
        currentShootCooldown = maxShootCooldown;

        double heading = angle - headingOffset;
        Vector2D fireDirection = new Vector2D(Math.cos(heading), Math.sin(heading));

        double spawnOffset = 25;
        double spawnX = position.getX() + Math.cos(heading) * spawnOffset;
        double spawnY = position.getY() + Math.sin(heading) * spawnOffset;

        return bulletFactory.createGameObject(spawnX, spawnY, fireDirection);
    }

    private boolean canShoot() {
        return currentShootCooldown <= 0;
    }

    @Override
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    public void loseLife(){
        this.lives--;
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public void reset() {
        position.setX(GameConfig.SCREEN_WIDTH / 2.0);
        position.setY(GameConfig.SCREEN_HEIGHT / 2.0);
        velocity = new Vector2D(0, 0);
        lives = 3;
        dead = false;
        notifyObservers();
    }
    public void setMove(boolean on) { move = on; }
    public void setRotateLeft(boolean on) { rotateLeft = on; }
    public void setRotateRight(boolean on) { rotateRight = on; }
    public int getLives() { return lives; }


    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public boolean isMoving()        { return move; }
    public boolean isRotatingLeft()  { return rotateLeft; }
    public boolean isRotatingRight() { return rotateRight; }
}