package main.worldStateManagement;

import main.gameobject.GameObject;
import main.conf.GameConfig;
import main.state.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class GameObjectContainer extends JPanel {

    private final List<GameObject> objects = new ArrayList<>();
    private final SpawnManager spawnManager;
    private GameState gameState;
    private  long lastTime;

    public GameObjectContainer(SpawnManager spawnManager, GameState state) {
        this.spawnManager = spawnManager;
        this.gameState = state;
        lastTime = System.nanoTime();
        setFocusable(true);
        setupInputBindings();

        Timer timer = new Timer(GameConfig.GAME_LOOP_DELAY_MS, e -> gameLoop());
        timer.start();

    }

    private void setupInputBindings() {
        for (int keyCode : GameConfig.BOUND_KEYS) {
            bindKey(keyCode);
        }
    }

    private void bindKey(int keyCode) {
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        String pressedActionName = "pressed_" + keyCode;
        String releasedActionName = "released_" + keyCode;

        inputMap.put(KeyStroke.getKeyStroke(keyCode, 0, false), pressedActionName);
        inputMap.put(KeyStroke.getKeyStroke(keyCode, 0, true), releasedActionName);

        actionMap.put(pressedActionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameState.keyPressed(keyCode, GameObjectContainer.this);
            }
        });

        actionMap.put(releasedActionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameState.keyReleased(keyCode, GameObjectContainer.this);
            }
        });
    }

    private void gameLoop() {

        long now = System.nanoTime();
        double deltaTime = (now - lastTime) / GameConfig.NANOS_PER_SECOND;
        lastTime = now;
        gameState.update(deltaTime, this);
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }

    public SpawnManager getSpawnManager() {
        return spawnManager;
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    public void updateObjects(double deltaTime) {
        for (GameObject obj : objects) {
            obj.update(deltaTime);
        }
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (GameObject obj : objects) {
            obj.draw(g2);
        }
    }
}