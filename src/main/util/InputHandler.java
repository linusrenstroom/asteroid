package main.util;

import main.conf.GameConfig;
import main.state.GameState;
import main.worldStateManagement.GameObjectContainer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class InputHandler {

    private GameState gameState;
    private final GameObjectContainer world;

    public InputHandler(JComponent component, GameState gameState, GameObjectContainer world) {
        this.gameState = gameState;
        this.world = world;

        setupKeyBindings(component);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    private void setupKeyBindings(JComponent component) {

        InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = component.getActionMap();

        for (int keyCode : GameConfig.BOUND_KEYS) {

            String pressed = "pressed_" + keyCode;
            String released = "released_" + keyCode;

            inputMap.put(KeyStroke.getKeyStroke(keyCode, 0, false), pressed);
            inputMap.put(KeyStroke.getKeyStroke(keyCode, 0, true), released);

            actionMap.put(pressed, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameState.keyPressed(keyCode, world);
                }
            });

            actionMap.put(released, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameState.keyReleased(keyCode, world);
                }
            });
        }
    }
}