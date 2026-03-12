package main.worldStateManagement;

import main.factory.BulletFactory;
import main.gameobject.Player;
import main.state.*;
import main.util.InputHandler;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    private final World world;
    private final Hud hud;
    private final GameLoop gameLoop;
    private GameState gameState;
    private InputHandler inputHandler;

    public Game(Player player, SpawnManager spawnManager, GameState initialState, BulletFactory bulletFactory) {
        this.world = new World(player, spawnManager, bulletFactory);
        this.hud = new Hud(player, world);
        this.gameState = initialState;
        this.gameLoop = new GameLoop(this::update, this::render);
        inputHandler = new InputHandler();
        rebindKeys();
        addKeyListener(inputHandler);
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

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        rebindKeys();
    }

    private void rebindKeys() {
        inputHandler.clearBindings();
        gameState.getKeyBindings(world, this::setGameState)
                .forEach(inputHandler::bind);
    }
}