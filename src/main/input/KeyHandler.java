package main.input;

import main.input.command.Command;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KeyHandler extends KeyAdapter {

    private final Map<Integer, Command> keyPressedCommands = new HashMap<>();
    private final Map<Integer, Command> keyReleasedCommands = new HashMap<>();
    private final Set<Integer> keysHeld = new HashSet<>();

    public void mapKeyPressed(int keyCode, Command command) {
        keyPressedCommands.put(keyCode, command);
    }

    public void mapKeyReleased(int keyCode, Command command) {
        keyReleasedCommands.put(keyCode, command);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        keysHeld.add(code);
        Command cmd = keyPressedCommands.get(code);
        if (cmd != null) cmd.execute();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        keysHeld.remove(code);
        Command cmd = keyReleasedCommands.get(code);
        if (cmd != null) cmd.execute();
    }

    public boolean isKeyHeld(int keyCode) {
        return keysHeld.contains(keyCode);
    }
}