package main;

import main.command.*;
import main.factory.BulletFactory;
import main.factory.GameObjectFactory;
import main.conf.GameConfig;
import main.gameobject.Player;
import main.state.MenuState;
import main.util.InputHandler;
import main.util.Point;
import main.worldStateManagement.Game;
import main.worldStateManagement.SpawnManager;

import javax.swing.*; // Added for SwingUtilities
import java.awt.event.KeyEvent;
import java.awt.Dimension;

public class Main extends JFrame {

    public Main() {
        super(GameConfig.WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameObjectFactory bulletFactory = new BulletFactory();
        Player player = new Player(
                new Point(
                        GameConfig.SCREEN_WIDTH * GameConfig.PLAYER_START_X_RATIO,
                        GameConfig.SCREEN_HEIGHT * GameConfig.PLAYER_START_Y_RATIO
                ),
                bulletFactory
        );

        SpawnManager spawnManager = new SpawnManager();
        Game game = new Game(player, spawnManager, new MenuState());
        game.setPreferredSize(new Dimension(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
        InputHandler input = new InputHandler(game);
        input.bind(KeyEvent.VK_W, new AccelerateCommand(player));
        input.bind(KeyEvent.VK_A, new RotateLeftCommand(player));
        input.bind(KeyEvent.VK_D, new RotateRightCommand(player));
        game.addKeyListener(input);
        game.setFocusable(true);

        add(game);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        game.requestFocusInWindow();
    }

    public static void main(String[] args) {SwingUtilities.invokeLater(Main::new);
    }
}