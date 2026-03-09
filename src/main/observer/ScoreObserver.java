package main.observer;


import main.gameobject.asteroids.Asteroid;

import java.awt.*;

public class ScoreObserver implements UiObserver {
    int scoreCounter = 0;


    @Override
    public void update(Observable subject) {
        if (subject instanceof Asteroid) {
            scoreCounter++;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawString("Score: " + scoreCounter, 20, 20);
    }
}