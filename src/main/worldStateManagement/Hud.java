package main.worldStateManagement;

import main.gameobject.Player;
import main.observer.LifeObserver;
import main.observer.ScoreObserver;
import main.observer.SoundObserver;
import main.observer.UiObserver;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Hud {
    private final List<UiObserver> observers = new ArrayList<>();

    public Hud(Player player, World world) {
        LifeObserver lifeObserver = new LifeObserver();
        ScoreObserver scoreObserver = new ScoreObserver();
        SoundObserver soundObserver = new SoundObserver();

        player.addObserver(lifeObserver);
        world.addObserver(soundObserver);
        world.addObserver(scoreObserver);
        player.addObserver(scoreObserver);

        observers.add(lifeObserver);
        observers.add(scoreObserver);

    }

    public void draw(Graphics2D g2) {
        for (UiObserver observer : observers) {
            observer.draw(g2);
        }
    }
}
