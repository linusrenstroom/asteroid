package main.util;

import main.worldStateManagement.GameObjectContainer;
import main.state.GameState;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class InputHandler {

    private final GameObjectContainer world;

    public InputHandler(GameObjectContainer world) {
        this.world = world;
        setupKeyBindings(world);
    }

    private void setupKeyBindings(JComponent component) {
        InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = component.getActionMap();
        int[] keys = {
                java.awt.event.KeyEvent.VK_W,
                java.awt.event.KeyEvent.VK_A,
                java.awt.event.KeyEvent.VK_S,
                java.awt.event.KeyEvent.VK_D,
                java.awt.event.KeyEvent.VK_SPACE,
                java.awt.event.KeyEvent.VK_P,
                java.awt.event.KeyEvent.VK_ESCAPE
        };

        for (int keyCode : keys) {
            String pressed = "pressed_" + keyCode;
            String released = "released_" + keyCode;

            inputMap.put(KeyStroke.getKeyStroke(keyCode, 0, false), pressed);
            inputMap.put(KeyStroke.getKeyStroke(keyCode, 0, true), released);

            actionMap.put(pressed, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GameState current = world.getGameState();
                    if (current != null) current.keyPressed(keyCode, world);
                }
            });

            actionMap.put(released, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GameState current = world.getGameState();
                    if (current != null) current.keyReleased(keyCode, world);
                }
            });
        }
    }
}