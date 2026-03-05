package main.state;

import main.gameobject.GameObject;
import main.worldStateManagement.GameObjectContainer;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RunningState implements GameState{


    @Override
    public void update(double deltaTime, GameObjectContainer context) {

        context.getSpawnManager().update(deltaTime, context.getObjects());
        context.updateObjects(deltaTime);
    }

    @Override
    public void draw(Graphics2D g) {
//Kanske rita ut score
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
