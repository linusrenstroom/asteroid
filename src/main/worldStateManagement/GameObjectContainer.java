package main.worldStateManagement;

import main.gameobject.GameObject;
import main.conf.GameConfig;
import main.state.GameState;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameObjectContainer extends JPanel {

    private final List<GameObject> objects = new ArrayList<>();
    private final List<GameObject> objectsToAdd = new ArrayList<>();
    private final SpawnManager spawnManager;
    private GameState gameState;
    private long lastTime;

    public GameObjectContainer(SpawnManager spawnManager, GameState initialState) {
        this.spawnManager = spawnManager;
        this.gameState = initialState;
        this.lastTime = System.nanoTime();
        setFocusable(true);
        setBackground(Color.DARK_GRAY);
        Timer timer = new Timer(GameConfig.GAME_LOOP_DELAY_MS, e -> gameLoop());
        timer.start();
    }

    private void gameLoop() {
        long now = System.nanoTime();
        double deltaTime = (now - lastTime) / GameConfig.NANOS_PER_SECOND;
        lastTime = now;

        gameState.update(deltaTime, this);

        updateObjects(deltaTime);
        checkCollisions();

        synchronizeObjects();
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }

    private void checkCollisions() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject a = objects.get(i);
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject b = objects.get(j);
                if (a.collidesWith(b)) {
                    a.onCollision(b);
                    b.onCollision(a);
                    notifyObservers();
                }
            }
        }
    }
    private void synchronizeObjects() {
        if (!objectsToAdd.isEmpty()) {
            objects.addAll(objectsToAdd);
            objectsToAdd.clear();
        }
        objects.removeIf(GameObject::isDead);
    }

    public void updateObjects(double deltaTime) {
        for (GameObject obj : objects) {
            obj.update(deltaTime);
        }
    }

    public void addObject(GameObject object) {
        objectsToAdd.add(object);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (GameObject obj : objects) {
            obj.draw(g2);
        }
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    public SpawnManager getSpawnManager() {
        return spawnManager;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

}