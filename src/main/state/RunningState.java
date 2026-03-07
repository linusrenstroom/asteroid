package main.state;

import main.gameobject.Player;
import main.worldStateManagement.GameObjectContainer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RunningState implements GameState {

    public RunningState() {

    }
    @Override
    public void update(double deltaTime, GameObjectContainer context) {
        context.updateObjects(deltaTime);
        context.checkCollisions();
        context.getSpawnManager().update(deltaTime, context.getObjects());
    }

    @Override
    public void keyPressed(int keyCode, GameObjectContainer context) {

    }

    @Override
    public void keyReleased(int keyCode, GameObjectContainer context) {

    }

    @Override
    public void draw(Graphics2D g) {}
}