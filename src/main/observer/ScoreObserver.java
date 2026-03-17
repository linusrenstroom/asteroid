package main.observer;

import main.conf.GameConfig;

import java.awt.*;

public class ScoreObserver implements UiObserver {
    int scoreCounter = 0;


    @Override
    public void onEvent(Observable subject, Event event) {
        if (event == Event.ASTEROID_DESTROYED) {
            scoreCounter++;
        }if(event == Event.PLAYER_DIED){
            scoreCounter = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Score: " + scoreCounter, GameConfig.SCREEN_WIDTH/2, GameConfig.SCREEN_HEIGHT -10);
    }
}