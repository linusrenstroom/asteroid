// Game.java — passes this::setGameState as the changeState callback
package main.worldStateManagement;

import main.gameobject.Player;
import main.state.GameState;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    private final World world;
    private final Hud hud;
    private final GameLoop gameLoop;
    private GameState gameState;

    public Game(Player player, SpawnManager spawnManager, GameState initialState) {
        this.world = new World(player, spawnManager);
        this.hud = new Hud(player);
        this.gameState = initialState;
        this.gameLoop = new GameLoop(this::update, this::render);

        setFocusable(true);
        setBackground(Color.DARK_GRAY);
        gameLoop.start();
    }

    private void update(double deltaTime) {
        gameState.update(deltaTime, world, this::setGameState);
    }

    private void render() {
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        world.draw(g2);
        gameState.draw(g2);
        hud.draw(g2);
    }

    public void handleKeyPressed(int keyCode) {
        gameState.keyPressed(keyCode, world, this::setGameState);
    }

    public void handleKeyReleased(int keyCode) {
        gameState.keyReleased(keyCode, world, this::setGameState);
    }

    public void setGameState(GameState gameState) { this.gameState = gameState; }
    public GameState getGameState()               { return gameState; }
    public World getWorld()                       { return world; }
    public Hud getHud()                           { return hud; }
}