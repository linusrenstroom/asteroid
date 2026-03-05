package main.worldStateManagement;

import main.gameobject.GameObject;
import main.state.GameState;
import main.state.RunningState;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameObjectContainer extends JPanel {

    private final List<GameObject> objects = new ArrayList<>();
    private final SpawnManager spawnManager;
    private GameState gameState;
    private  long lastTime;

    public GameObjectContainer(SpawnManager spawnManager, GameState state) {
        this.spawnManager = spawnManager;
        this.gameState = state;
        lastTime = System.nanoTime();

        Timer timer = new Timer(16, e -> gameLoop());
        timer.start();

    }

    private void gameLoop() {

        long now = System.nanoTime();
        double deltaTime = (now - lastTime) / 1_000_000_000.0;
        lastTime = now;
        gameState.update(deltaTime, this);
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }

    public SpawnManager getSpawnManager() {
        return spawnManager;
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    public void updateObjects(double deltaTime) {
        for (GameObject obj : objects) {
            obj.update(deltaTime);
        }
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (GameObject obj : objects) {
            obj.draw(g2);
        }
    }
}