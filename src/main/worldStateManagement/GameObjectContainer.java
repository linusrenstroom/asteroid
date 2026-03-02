package main.worldStateManagement;

import main.gameobject.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameObjectContainer extends JPanel {
    private final List<GameObject> objects = new ArrayList<>();
    private final SpawnManager spawnManager;
    private long lastTime;

    public GameObjectContainer(SpawnManager spawnManager){
        this.spawnManager = spawnManager;
        lastTime = System.nanoTime();

        Timer timer = new Timer(16, e -> gameLoop());
        timer.start();
    }

    public void addObject(GameObject obj){
        objects.add(obj);

    }

    private void gameLoop() {
        long now = System.nanoTime();
        double deltaTime = (now - lastTime) / 1_000_000_000.0;
        lastTime = now;

        spawnManager.update(deltaTime, objects);

        for (GameObject obj : objects) {
            obj.update(deltaTime);

        }
        objects.removeIf(GameObject::isDead);
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2 = (Graphics) g;

        for (GameObject obj : objects) {
            obj.draw(g2);
        }
    }
}
