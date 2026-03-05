package main.state;

import main.input.command.*;
import main.gameobject.Player;
import main.worldStateManagement.GameObjectContainer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RunningState implements GameState {

    private final Player player;
    private final Map<Integer, Command> keyPressedCommands = new HashMap<>();
    private final Map<Integer, Command> keyReleasedCommands = new HashMap<>();

    public RunningState(Player player, double deltaTime) {
        this.player = player;
        keyPressedCommands.put(KeyEvent.VK_W, new MoveForwardCommand(player, deltaTime));
        keyPressedCommands.put(KeyEvent.VK_A, new RotateLeftCommand(player, -0.1));
        keyPressedCommands.put(KeyEvent.VK_D, new RotateRightCommand(player, 0.1));
        keyPressedCommands.put(KeyEvent.VK_SPACE, new ShootCommand(player));
        keyReleasedCommands.put(KeyEvent.VK_W, () -> player.stopAcceleration());
    }

    private final Set<Integer> keysHeld = new HashSet<>();

    @Override
    public void keyPressed(KeyEvent e) {
        keysHeld.add(e.getKeyCode());
        Command command = keyPressedCommands.get(e.getKeyCode());
        if (command != null) command.execute();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysHeld.remove(e.getKeyCode());
        Command command = keyReleasedCommands.get(e.getKeyCode());
        if (command != null) command.execute();
    }

    @Override
    public void update(double deltaTime, GameObjectContainer context) {
        context.getSpawnManager().update(deltaTime, context.getObjects());
        context.updateObjects(deltaTime);
    }

    @Override
    public void draw(Graphics2D g) {
        // draw things like score, etc.
    }
}