// World.java — remove setGameState entirely, it doesn't belong here
package main.worldStateManagement;

import main.Vector2D;
import main.conf.GameConfig;
import main.factory.BulletFactory;
import main.gameobject.GameObject;
import main.gameobject.Player;
import main.gameobject.asteroids.Asteroid;
import main.observer.Event;
import main.observer.Observable;
import main.observer.Observer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class World implements Observer {
    private final List<GameObject> objects = new ArrayList<>();
    private final Player player;
    private final SpawnManager spawnManager;
    private final BulletFactory bulletFactory;

    public World(Player player, SpawnManager spawnManager, BulletFactory bulletFactory) {
        this.player = player;
        this.spawnManager = spawnManager;
        this.bulletFactory = bulletFactory; // injected, not constructed here
        objects.add(player);
        player.addObserver(this);
    }

    public void update(double deltaTime) {
        spawnManager.update(deltaTime, objects);
        for (GameObject obj : objects) {
            obj.update(deltaTime);
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
    }

    public void draw(Graphics2D g2) {
        for (GameObject obj : objects) {
            obj.draw(g2);
        }
    }

    public void reset() {
        objects.clear();
        player.reset();
        objects.add(player);
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void onEvent(Observable subject, Event event) {
        switch (event) {
            case SHOT_FIRED -> {
                if (subject instanceof Player p) {
                    double angle = p.getHeadingAngle();
                    Vector2D direction = new Vector2D(
                            Math.cos(angle) * GameConfig.BULLET_SPEED,
                            Math.sin(angle) * GameConfig.BULLET_SPEED
                    );
                    addObject(bulletFactory.createGameObject(p.getPosition(), direction));
                }
            }
            case PLAYER_DIED -> {}
        }
    }

    public void addAsteroidObserver(Observer observer) {
        objects.stream()
                .filter(o -> o instanceof Asteroid)
                .forEach(a -> a.addObserver(observer));

        spawnManager.addAsteroidObserver(observer);
    }
}