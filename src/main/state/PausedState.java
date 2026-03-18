// PausedState.java
package main.state;

import main.command.ChangeStateCommand;
import main.command.Command;
import main.conf.GameConfig;
import main.worldStateManagement.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class PausedState implements GameState {
    private final GameState previousState;

    public PausedState(GameState previousState) {
        this.previousState = previousState;
    }

    @Override
    public void update(double deltaTime, World world, Consumer<GameState> changeState) {}

    @Override
    public void draw(Graphics2D g, World world) {
        g.setColor(new Color(0, 0, 0, GameConfig.PAUSED_OVERLAY_ALPHA));
        g.fillRect(0, 0, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        g.setColor(Color.WHITE);
        g.drawString(GameConfig.PAUSED_TEXT, GameConfig.SCREEN_WIDTH / 2, GameConfig.SCREEN_HEIGHT / 2);
    }

    @Override
    public Map<Integer, Command> getKeyBindings(World world, Consumer<GameState> changeState) {
        Map<Integer, Command> bindings = new HashMap<>();
        bindings.put(KeyEvent.VK_ESCAPE, new ChangeStateCommand(
                () -> previousState, changeState
        ));
        return bindings;
    }

    public GameState getPreviousState(){
        return previousState;
    }

}