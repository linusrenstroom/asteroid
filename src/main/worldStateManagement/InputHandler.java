package main.worldStateManagement;

import main.command.Command;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

// InputHandler.java — delegates to Game, not directly to state
public class InputHandler implements KeyListener {
    private final Game game;
    private final Map<Integer, Command> bindings = new HashMap<>();

    public InputHandler(Game game) {
        this.game = game;
    }

    public void bind(int keyCode, Command command) {
        bindings.put(keyCode, command);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Command cmd = bindings.get(e.getKeyCode());
        if (cmd != null) cmd.execute();
        game.handleKeyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        game.handleKeyReleased(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}