package main.util;

import main.command.Command;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class InputHandler implements KeyListener {

    private Map<Integer, Command> commands = new HashMap<>();

    public void bind(int keyCode, Command command) {
        commands.put(keyCode, command);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Command command = commands.get(e.getKeyCode());

        if (command != null) {
            command.execute();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Command command = commands.get(e.getKeyCode());

        if (command != null) {
            command.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}