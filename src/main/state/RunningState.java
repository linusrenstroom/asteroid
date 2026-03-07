package main.state;
import main.worldStateManagement.GameObjectContainer;
import java.awt.*;
import java.awt.event.KeyEvent;

public class RunningState implements GameState {

    public RunningState() {
    }
    @Override
    public void update(double deltaTime, GameObjectContainer context) {
        context.updateObjects(deltaTime);
        context.checkCollisions();
        context.getSpawnManager().update(deltaTime, context.getObjects());
        context.updateObjects(deltaTime);

    }

    @Override
    public void draw(Graphics2D g) {}

    @Override
    public void keyPressed(int keyCode, GameObjectContainer context) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            context.setGameState(new PausedState(this));
        }
    }

    @Override
    public void keyReleased(int keyCode, GameObjectContainer context) {

    }
}