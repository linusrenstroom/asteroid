package main.state;

import main.worldStateManagement.GameObjectContainer;

import java.awt.*;
import java.awt.event.KeyEvent;

public interface GameState {
    public void update(double deltaTime, GameObjectContainer context);
    public void draw(Graphics2D g);
    public void keyPressed(KeyEvent e);
    public void keyReleased(KeyEvent e);
}
