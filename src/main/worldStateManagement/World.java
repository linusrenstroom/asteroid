// World.java — remove setGameState entirely, it doesn't belong here
package main.worldStateManagement;

import main.gameobject.GameObject;
import main.gameobject.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class World {
    private final List<GameObject> objects = new ArrayList<>();
    private final Player player;
    private final SpawnManager spawnManager;

    public World(Player player, SpawnManager spawnManager) {
        this.player = player;
        this.spawnManager = spawnManager;
        objects.add(player);
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

    public void addObject(GameObject object)      { objects.add(object); }

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

    public List<GameObject> getObjects()          { return Collections.unmodifiableList(objects); }
    public Player getPlayer()                     { return player; }
    public SpawnManager getSpawnManager()         { return spawnManager; }
}