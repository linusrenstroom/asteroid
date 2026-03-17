// World.java — remove setGameState entirely, it doesn't belong here
package main.worldStateManagement;

import main.Vector2D;
import main.conf.GameConfig;
import main.factory.BulletFactory;
import main.gameobject.EnemyShip;
import main.gameobject.Explosion;
import main.gameobject.GameObject;
import main.gameobject.Player;
import main.gameobject.asteroids.Asteroid;
import main.observer.Event;
import main.observer.Observable;
import main.observer.Observer;
import main.util.Point;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;


public class World implements Observer, WorldMediator {
    private final List<GameObject> objects = new ArrayList<>();
    private final Player player;
    private final SpawnManager spawnManager;
    private final BulletFactory bulletFactory;
    private final List<Observer> gameObservers = new ArrayList<>();

    public World(Player player, SpawnManager spawnManager, BulletFactory bulletFactory) {
        this.player = player;
        this.spawnManager = spawnManager;
        this.bulletFactory = bulletFactory;
        addObject(player);
        player.addObserver(this);
        spawnManager.addObserver(this);
    }

    public void update(double deltaTime) {
        spawnManager.update(deltaTime, this);
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);
            obj.update(deltaTime, this);
        }
    }

    public void checkCollisions() {
        int size = objects.size();
        for (int i = 0; i < size; i++) {
            GameObject a = objects.get(i);
            for (int j = i + 1; j < size; j++) {
                GameObject b = objects.get(j);
                if (a.collidesWith(b)) {
                    a.onCollision(b);
                    b.onCollision(a);
                }
            }
        }
    }

    public void removeDeadObjects() {
        objects.removeIf(GameObject::isDead);
    }

    public void addObject(GameObject object) {
        objects.add(object);
        for (Observer o : gameObservers) {
            object.addObserver(o);
        }
    }

    public void draw(Graphics2D g2) {
        for (GameObject obj : objects) {
            obj.draw(g2);
        }
    }

    public void reset() {
        objects.clear();
        player.reset();
        addObject(player);
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void onEvent(Observable subject, Event event) {
        switch (event) {
            case SHOT_FIRED -> {
                if (subject instanceof Player p) {
                    handlePlayerShot(p);
                }
            }
            case PLAYER_DIED -> {
            }
            case ENEMY_SHOT_FIRED -> {
                if (subject instanceof EnemyShip e) {
                    handleEnemyShot(e);
                }
            }
            case ASTEROID_DESTROYED -> {
                if (subject instanceof Asteroid a) {
                    addObject(new Explosion(new Point(a.getPosition().getX(), a.getPosition().getY())));
                }
            }
        }
    }
    private void handleEnemyShot(EnemyShip e) {
        double angle = e.getAngle() - Math.toRadians(90);
        double dx = Math.cos(angle);
        double dy = Math.sin(angle);
        double offsetDistance = 20;

        Vector2D direction = new Vector2D(dx * GameConfig.ENEMY_BULLET_SPEED, dy * GameConfig.ENEMY_BULLET_SPEED);
        Point spawnPos = new Point(e.getPosition().getX() + dx * offsetDistance, e.getPosition().getY() + dy * offsetDistance);

        addObject(bulletFactory.createEnemyBullet(spawnPos, direction));
    }
    private void handlePlayerShot(Player p) {
        double angle = p.getHeadingAngle();
        double offsetDistance = 20;

        Vector2D direction = new Vector2D(Math.cos(angle) * GameConfig.PLAYER_BULLET_SPEED, Math.sin(angle) * GameConfig.PLAYER_BULLET_SPEED);
        Point spawnPos = new Point(p.getPosition().getX() + Math.cos(angle) * offsetDistance, p.getPosition().getY() + Math.sin(angle) * offsetDistance);

        addObject(bulletFactory.createGameObject(spawnPos, direction));
    }

    public void addObserver(Observer observer) {

    }

    public void addGameObserver(Observer observer) {
        gameObservers.add(observer);
        for (GameObject obj : objects) {
            obj.addObserver(observer);
        }
    }
}