package main.worldStateManagement;

import main.gameobject.Player;
import main.observer.LifeObserver;
import main.observer.SoundObserver;
import main.observer.UiObserver;
import main.conf.GameConfig;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Hud {
    private final List<UiObserver> observers = new ArrayList<>();
    private final World world;

    public Hud(Player player, World world) {
        this.world = world;
        LifeObserver lifeObserver = new LifeObserver();
        SoundObserver soundObserver = new SoundObserver();
        world.addObserver(lifeObserver);
        player.addObserver(lifeObserver);
        world.addObserver(soundObserver);

        observers.add(lifeObserver);

    }

    public void draw(Graphics2D g2) {
        for (UiObserver observer : observers) {
            observer.draw(g2);
        }

        g2.setColor(Color.WHITE);
        g2.drawString("Score: " + world.getScore(), 20, 30);
        g2.drawString("HighScore: " + world.getHighScore(), GameConfig.SCREEN_WIDTH - 150, 30);
    }
}
