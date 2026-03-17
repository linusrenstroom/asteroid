package main.worldStateManagement;

import main.conf.GameConfig;
import main.observer.Observable;


import javax.swing.*;
import java.util.function.DoubleConsumer;

public class GameLoop extends Observable {
    private final Timer timer;
    private long lastTime;
    private final DoubleConsumer onUpdate;
    private final Runnable onRender;

    public GameLoop(DoubleConsumer onUpdate, Runnable onRender) {
        this.onUpdate = onUpdate;
        this.onRender = onRender;
        this.lastTime = System.nanoTime();
        this.timer = new Timer(GameConfig.GAME_LOOP_DELAY_MS, e -> tick());
    }

    private void tick() {
        long now = System.nanoTime();
        double deltaTime = (now - lastTime) / GameConfig.NANOS_PER_SECOND;
        lastTime = now;
        onUpdate.accept(deltaTime);
        onRender.run();
    }

    public void start() {
        lastTime = System.nanoTime(); // reset on start to avoid huge first delta
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public boolean isRunning() {
        return timer.isRunning();
    }
}
