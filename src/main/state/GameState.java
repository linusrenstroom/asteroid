package main.state;

import main.worldStateManagement.GameObjectContainer;

import java.awt.*;

public interface GameState {
    public void update(double deltaTime, GameObjectContainer context);
    public void draw(Graphics2D g);
    public void keyPressed(int keyCode, GameObjectContainer context);
    public void keyReleased(int keyCode, GameObjectContainer context);
}
