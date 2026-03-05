package main.state;

import main.conf.GameConfig;
import main.worldStateManagement.GameObjectContainer;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PausedState implements GameState{
    @Override
    public void update(double deltaTime, GameObjectContainer context) {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(0,0,0,128));
        g.fillRect(0,0, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        g.setColor(Color.WHITE);
        g.drawString("PAUSAT", GameConfig.SCREEN_WIDTH/2, GameConfig.SCREEN_HEIGHT/2);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
