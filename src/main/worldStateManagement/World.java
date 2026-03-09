package main.worldStateManagement;

import main.gameobject.GameObject;
import main.gameobject.Player;
import main.state.GameState;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class World {
    private final List<GameObject> objects = new ArrayList<>();
    private final SpawnManager spawnManager;
    private final Player player;
    private GameState gameState;

    public World(Player player, SpawnManager spawnManager, GameState initialState) {
        this.player = player;
        this.spawnManager = spawnManager;
        this.gameState = initialState;
        objects.add(player);
    }

    public void update(double deltaTime) {
        gameState.update(deltaTime, this);
        for (GameObject obj : new ArrayList<>(objects)) {  // copy avoids ConcurrentModificationException
            obj.update(deltaTime);
        }
        checkCollisions();
        removeDeadObjects();
        spawnManager.update(deltaTime, objects);
    }

    public void draw(Graphics2D g) {
        for (GameObject obj : objects) {
            obj.draw(g);
        }
    }

    public void checkCollisions() {
        List<GameObject> snapshot = new ArrayList<>(objects);
        int size = snapshot.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                GameObject a = snapshot.get(i);
                GameObject b = snapshot.get(j);
                if (a.collidesWith(b)) {
                    a.onCollision(b);
                    b.onCollision(a);
                }
            }
        }
    }

    private void removeDeadObjects() {
        objects.removeIf(GameObject::isDead);
    }

    public void addObject(GameObject obj)     { objects.add(obj); }
    public List<GameObject> getObjects()      { return new ArrayList<>(objects); } // defensive copy
    public Player getPlayer()                 { return player; }
    public SpawnManager getSpawnManager()     { return spawnManager; }
    public GameState getGameState()           { return gameState; }
    public void setGameState(GameState state) { this.gameState = state; }
}