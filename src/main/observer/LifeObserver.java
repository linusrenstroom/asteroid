package main.observer;

import main.gameobject.Player;

import java.awt.*;

public class LifeObserver implements UiObserver {

    private int lives = 3;

    @Override
    public void update(Observable subject) {
        if (subject instanceof Player) {
            Player player = (Player) subject;
            this.lives = player.getLives();
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);

        int heartWidth = 20;   // width of heart
        int heartHeight = 20;  // height of heart
        int spacing = 5;       // space between hearts
        int x = 20;            // starting x
        int y = 20;            // starting y

        for (int i = 0; i < lives; i++) {
            drawHeart(g2, x + i * (heartWidth + spacing), y, heartWidth, heartHeight);
        }
    }
    private void drawHeart(Graphics2D g, int x, int y, int width, int height) {
        int[] xs = {x + width / 2, x + width, x + width * 3 / 4, x + width / 2, x + width / 4, x};
        int[] ys = {y + height / 5, y + height / 5, y + height * 3 / 5, y + height, y + height * 3 / 5, y + height / 5};
        g.fillPolygon(xs, ys, xs.length);

        // Draw the two arcs (top lobes of heart)
        g.fillOval(x, y, width / 2, height / 2);
        g.fillOval(x + width / 2, y, width / 2, height / 2);
    }
}