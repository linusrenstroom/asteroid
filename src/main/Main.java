// Main.java
package main;

import main.command.AccelerateCommand;
import main.command.RotateLeftCommand;
import main.command.RotateRightCommand;
import main.command.ShootCommand;
import main.factory.AsteroidFactory;
import main.factory.BulletFactory;
import main.factory.GameObjectFactory;
import main.conf.GameConfig;
import main.gameobject.Player;
import main.state.MenuState;
import main.util.InputHandler;
import main.util.Point;
import main.worldStateManagement.Game;
import main.worldStateManagement.SpawnManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Main extends JFrame {

    public Main() {
        super(GameConfig.WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Player player = new Player(new Point(
                GameConfig.SCREEN_WIDTH * GameConfig.PLAYER_START_X_RATIO,
                GameConfig.SCREEN_HEIGHT * GameConfig.PLAYER_START_Y_RATIO
        ));

        SpawnManager spawnManager = new SpawnManager(new AsteroidFactory());
        Game game = new Game(player, spawnManager, new MenuState());
        game.setPreferredSize(new Dimension(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));

        GameObjectFactory bulletFactory = new BulletFactory();
        InputHandler input = new InputHandler(game);
        input.bind(KeyEvent.VK_W, new AccelerateCommand(player));
        input.bind(KeyEvent.VK_A, new RotateLeftCommand(player));
        input.bind(KeyEvent.VK_D, new RotateRightCommand(player));
        input.bind(KeyEvent.VK_SPACE, new ShootCommand(player, bulletFactory));
        game.addKeyListener(input);
        game.setFocusable(true);
        game.requestFocusInWindow();

        add(game);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}