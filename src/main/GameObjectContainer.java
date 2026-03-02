package main;

import main.gameobject.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameObjectContainer extends JPanel {
    private final List<GameObject> objects = new ArrayList<>();
    private long lastTime;

    public GameObjectContainer(){
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

        for (GameObject obj : objects) {
            obj.update(deltaTime);
        }

        repaint();
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
