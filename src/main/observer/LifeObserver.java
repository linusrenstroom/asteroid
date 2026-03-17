package main.observer;

import main.gameobject.Player;

import java.awt.*;

public class LifeObserver implements UiObserver {

    private int lives = 3;

    @Override
    public void onEvent(Observable subject, Event event) {
        if (event == Event.LOSE_LIFE && subject instanceof Player player) {
            lives = player.getLives();
        }
        if (event == Event.PLAYER_DIED){
            lives = 3;
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);

        int heartWidth = 20;
        int heartHeight = 20;
        int spacing = 5;
        int x = 20;
        int y = 50;

        for (int i = 0; i < lives; i++) {
            drawHeart(g2, x + i * (heartWidth + spacing), y, heartWidth, heartHeight);
        }
    }
    private void drawHeart(Graphics2D g, int x, int y, int width, int height) {
        int[] xs = {x + width / 2, x + width, x + width * 3 / 4, x + width / 2, x + width / 4, x};
        int[] ys = {y + height / 5, y + height / 5, y + height * 3 / 5, y + height, y + height * 3 / 5, y + height / 5};
        g.fillPolygon(xs, ys, xs.length);

        g.fillOval(x, y, width / 2, height / 2);
        g.fillOval(x + width / 2, y, width / 2, height / 2);
    }
}