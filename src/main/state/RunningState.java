package main.state;

import main.gameobject.Player;
import main.worldStateManagement.GameObjectContainer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RunningState implements GameState {

    private final Player player;
    private final Map<Integer, Consumer<GameObjectContainer>> keyPressedActions = new HashMap<>();
    private final Map<Integer, Consumer<GameObjectContainer>> keyReleasedActions = new HashMap<>();
    private boolean isSpaceDown = false;

    public RunningState(Player player) {
        this.player = player;

        keyPressedActions.put(KeyEvent.VK_W, (ctx) -> player.setMove(true));
        keyPressedActions.put(KeyEvent.VK_A, (ctx) -> player.setRotateLeft(true));
        keyPressedActions.put(KeyEvent.VK_D, (ctx) -> player.setRotateRight(true));
        keyPressedActions.put(KeyEvent.VK_SPACE, (ctx) -> isSpaceDown = true);
        keyReleasedActions.put(KeyEvent.VK_W, (ctx) -> player.setMove(false));
        keyReleasedActions.put(KeyEvent.VK_A, (ctx) -> player.setRotateLeft(false));
        keyReleasedActions.put(KeyEvent.VK_D, (ctx) -> player.setRotateRight(false));
        keyReleasedActions.put(KeyEvent.VK_SPACE, (ctx) -> isSpaceDown = false);
        keyPressedActions.put(KeyEvent.VK_P, (ctx) -> ctx.setGameState(new PausedState(this)));
        keyPressedActions.put(KeyEvent.VK_ESCAPE, (ctx) -> ctx.setGameState(new PausedState(this)));
    }
    //TODO Se över hela rörelse mekaniken.
    //TODO GameState borde inte kontrollera player.shoot.
    //TODO RunningState bör inte behöva en referens till player
    @Override
    public void update(double deltaTime, GameObjectContainer context) {
        if (isSpaceDown) {
            player.shoot(context);
        }
        context.updateObjects(deltaTime);
        context.checkCollisions();
        context.getSpawnManager().update(deltaTime, context.getObjects());
        context.updateObjects(deltaTime);
    }

    @Override
    public void keyPressed(int keyCode, GameObjectContainer context) {
        Consumer<GameObjectContainer> action = keyPressedActions.get(keyCode);
        if (action != null) action.accept(context);

    }

    @Override
    public void keyReleased(int keyCode, GameObjectContainer context) {
        Consumer<GameObjectContainer> action = keyReleasedActions.get(keyCode);
        if (action != null) action.accept(context);
    }

    @Override
    public void draw(Graphics2D g) {}
}