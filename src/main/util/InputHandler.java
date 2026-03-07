package main.util;

import main.command.Command;
import main.state.GameState;
import main.worldStateManagement.GameObjectContainer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class InputHandler implements KeyListener {

    private final Map<Integer, Command> commands = new HashMap<>();
    private final GameObjectContainer context;

    public InputHandler(GameObjectContainer context) {
        this.context = context;
    }

    public void bind(int keyCode, Command command) {
        commands.put(keyCode, command);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Command command = commands.get(e.getKeyCode());
        if (command != null) {
            command.execute();
        }

        GameState state = context.getGameState();
        if (state != null) {
            state.keyPressed(e.getKeyCode(), context);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Command command = commands.get(e.getKeyCode());
        if (command != null) {
            command.stop();
        }

        GameState state = context.getGameState();
        if (state != null) {
            state.keyReleased(e.getKeyCode(), context);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}