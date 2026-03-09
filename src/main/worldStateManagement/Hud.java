package main.worldStateManagement;

import main.gameobject.Player;
import main.observer.LifeObserver;
import main.observer.UiObserver;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Hud {
    private final List<UiObserver> observers = new ArrayList<>();

    public Hud(Player player) {
        LifeObserver lifeObserver = new LifeObserver();
        player.addObserver(lifeObserver);
        observers.add(lifeObserver);

        // Add more observers here, e.g. ScoreObserver
        // ScoreObserver scoreObserver = new ScoreObserver();
        // player.addObserver(scoreObserver);
        // observers.add(scoreObserver);
    }

    public void draw(Graphics2D g2) {
        for (UiObserver observer : observers) {
            observer.draw(g2);
        }
    }

    public void addObserver(UiObserver observer) {
        observers.add(observer);
    }
}
