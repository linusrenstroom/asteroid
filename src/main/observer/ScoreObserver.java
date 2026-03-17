package main.observer;

import main.HighScoreHandler;
import main.conf.GameConfig;
import main.state.GameOverState;

import java.awt.*;

public class ScoreObserver implements UiObserver {
    private final HighScoreHandler highScoreHandler = new HighScoreHandler();
    private int highScore = 0;
    int scoreCounter = 0;

    public ScoreObserver() {
        highScore = highScoreHandler.getHighScore(); // load at start
    }

    @Override
    public void onEvent(Observable subject, Event event) {
        if (event == Event.ASTEROID_DESTROYED) {
            scoreCounter++;
        }if(event == Event.PLAYER_DIED){
            System.out.println("PLAYER_DIED triggered. Saving score: " + scoreCounter);
            highScoreHandler.save(scoreCounter);
            highScore = Math.max(highScore, scoreCounter);
            scoreCounter = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);

        // Top-left: current score
        g.drawString("Score: " + scoreCounter, 20, 30);

        // Top-right: high score
        g.drawString("HighScore: " + highScore,
                GameConfig.SCREEN_WIDTH - 150, 30);
    }
}