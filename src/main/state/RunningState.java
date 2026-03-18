// RunningState.java
package main.state;

import main.command.*;
import main.worldStateManagement.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
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
    public void draw(Graphics2D g, World world) {}

    @Override
    public Map<Integer, Command> getKeyBindings(World world, Consumer<GameState> changeState) {
        Map<Integer, Command> bindings = new HashMap<>();
        bindings.put(KeyEvent.VK_W, new AccelerateCommand(world.getPlayer()));
        bindings.put(KeyEvent.VK_A, new RotateLeftCommand(world.getPlayer()));
        bindings.put(KeyEvent.VK_D, new RotateRightCommand(world.getPlayer()));
        bindings.put(KeyEvent.VK_SPACE, new ShootCommand(world.getPlayer()));
        bindings.put(KeyEvent.VK_ESCAPE, new ChangeStateCommand(
                () -> new PausedState(this), changeState
        ));
        return bindings;
    }

}