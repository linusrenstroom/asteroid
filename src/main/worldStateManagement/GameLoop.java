package main.worldStateManagement;

import main.conf.GameConfig;
import main.worldStateManagement.GamePanel;

import javax.swing.Timer;
import java.awt.Toolkit;

public class GameLoop {
    private final World world;
    private final GamePanel panel;
    private long lastTime;
    private Timer timer;

    public GameLoop(World world, GamePanel panel) {
        this.world = world;
        this.panel = panel;
    }

    public void start() {
        lastTime = System.nanoTime();
        timer = new Timer(GameConfig.GAME_LOOP_DELAY_MS, e -> tick());
        timer.start();
    }

    public void stop()   { if (timer != null) timer.stop(); }
    public void pause()  { if (timer != null) timer.stop(); }
    public void resume() { lastTime = System.nanoTime(); if (timer != null) timer.start(); }

    private void tick() {
        long now = System.nanoTime();
        double deltaTime = (now - lastTime) / GameConfig.NANOS_PER_SECOND;
        lastTime = now;
        world.update(deltaTime);
        Toolkit.getDefaultToolkit().sync();
        panel.repaint();
    }
}