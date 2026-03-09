package main.gameobject;

import main.Vector2D;
import main.conf.GameConfig;
import main.factory.BulletFactory;
import main.factory.GameObjectFactory;
import main.gameobject.asteroids.Asteroid;
import main.observer.Observer; // Assuming this is your base interface
import main.observer.Observable;
import main.util.Point;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Player extends GameObject implements Observable {
    private final Polygon shipBoundingBox;
    private final double rotationSpeed = GameConfig.PLAYER_ROTATION_SPEED;
    private final double acceleration = GameConfig.PLAYER_ACCELERATION;
    private final double dragPerSecond = GameConfig.PLAYER_DRAG_PER_SECOND;
    private final double headingOffset = GameConfig.PLAYER_HEADING_OFFSET_RADIANS;
    private final double bulletSpeed = GameConfig.PLAYER_BULLET_SPEED;
    private final double maxShootCooldown = GameConfig.PLAYER_SHOOT_COOLDOWN;
    private GameObjectFactory bulletFactory = new BulletFactory();
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
    }

    @Override
    public void update(double deltaTime) {
        int rotationInput = (rotateRight ? 1 : 0) - (rotateLeft ? 1 : 0);
        angle += rotationInput * rotationSpeed * deltaTime;
        double heading = angle - headingOffset;
        if (move) {
            velocity = velocity.add(new Vector2D(
                    Math.cos(heading) * acceleration * deltaTime,
                    Math.sin(heading) * acceleration * deltaTime
            ));
        }
        if (currentShootCooldown > 0) {
            currentShootCooldown -= deltaTime;
        }
        velocity = velocity.multiply(Math.pow(dragPerSecond, deltaTime));

        position.setX(position.getX() + velocity.x * deltaTime);
        position.setY(position.getY() + velocity.y * deltaTime);

        handleScreenWrapping();
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform old = g.getTransform();
        g.translate(position.getX(), position.getY());
        g.rotate(angle);
        g.setColor(Color.WHITE);
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

    private void handleScreenWrapping() {
        if (position.getX() < 0) position.setX(GameConfig.SCREEN_WIDTH);
        else if (position.getX() > GameConfig.SCREEN_WIDTH) position.setX(0);

        if (position.getY() < 0) position.setY(GameConfig.SCREEN_HEIGHT);
        else if (position.getY() > GameConfig.SCREEN_HEIGHT) position.setY(0);
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

        double spawnOffset = 20;// e.g. 20.0
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

}