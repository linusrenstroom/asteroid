// RunningState.java
package main.state;

import main.worldStateManagement.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

public class RunningState implements GameState {

    @Override
    public void update(double deltaTime, World world, Consumer<GameState> changeState) {
        world.update(deltaTime);
        world.checkCollisions();
        world.removeDeadObjects();

        if (world.getPlayer().isDead()) {
            changeState.accept(new GameOverState());
        }
    }

    @Override
    public void draw(Graphics2D g) {}

    @Override
    public void keyPressed(int keyCode, World world, Consumer<GameState> changeState) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            changeState.accept(new PausedState(this));
        }
    }

    @Override
    public void keyReleased(int keyCode, World world, Consumer<GameState> changeState) {}
}