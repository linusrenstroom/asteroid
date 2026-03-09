// PausedState.java
package main.state;

import main.conf.GameConfig;
import main.worldStateManagement.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

public class PausedState implements GameState {
    private final GameState previousState;

    public PausedState(GameState previousState) {
        this.previousState = previousState;
    }

    @Override
    public void update(double deltaTime, World world, Consumer<GameState> changeState) {}

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(0, 0, 0, GameConfig.PAUSED_OVERLAY_ALPHA));
        g.fillRect(0, 0, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        g.setColor(Color.WHITE);
        g.drawString(GameConfig.PAUSED_TEXT, GameConfig.SCREEN_WIDTH / 2, GameConfig.SCREEN_HEIGHT / 2);
    }

    @Override
    public void keyPressed(int keyCode, World world, Consumer<GameState> changeState) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            changeState.accept(previousState);
        }
    }

    @Override
    public void keyReleased(int keyCode, World world, Consumer<GameState> changeState) {}
}