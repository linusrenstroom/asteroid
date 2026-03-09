package main.worldStateManagement;

import main.observer.Observable;
import main.observer.Observer;

public class HUD implements Observer {
    private int lives;
    private int score;

    @Override
    public void update(Observable subject) {
        if (source instanceof Player p) lives = p.getLives();
    }

    public void draw(Graphics2D g) {
        // draw lives, score, etc.
    }

}
