package main;

import main.command.AccelerateCommand;
import main.command.RotateLeftCommand;
import main.command.RotateRightCommand;
import main.command.ShootCommand;
import main.factory.*;
import main.conf.GameConfig;
import main.gameobject.GameObject;
import main.gameobject.Player;
import main.observer.ScoreObserver;
import main.state.GameOverState;
import main.state.MenuState;
import main.state.PausedState;
import main.state.RunningState;
import main.util.Point;
import main.worldStateManagement.GameObjectContainer;
import main.worldStateManagement.SpawnManager;
import main.util.InputHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Main extends JFrame {

    private GameObjectContainer world;
    private RunningState runningState;
    private PausedState pausedState;
    private GameOverState gameOverState;
    private MenuState menuState;


    public Main() {
        super(GameConfig.WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Player player = new Player(new Point(
                GameConfig.SCREEN_WIDTH * GameConfig.PLAYER_START_X_RATIO,
                GameConfig.SCREEN_HEIGHT * GameConfig.PLAYER_START_Y_RATIO
        ));

        runningState = new RunningState();

        gameOverState = new GameOverState();
        menuState = new MenuState();







        SpawnManager spawnManager = new SpawnManager();
        world = new GameObjectContainer(spawnManager, player, menuState);
        world.setPreferredSize(new Dimension(
                GameConfig.SCREEN_WIDTH,
                GameConfig.SCREEN_HEIGHT
        ));
        add(world);
        GameObjectFactory bulletFactory = new BulletFactory();
        InputHandler input = new InputHandler(world);
        input.bind(KeyEvent.VK_W, new AccelerateCommand(player));
        input.bind(KeyEvent.VK_A, new RotateLeftCommand(player));
        input.bind(KeyEvent.VK_D, new RotateRightCommand(player));
        input.bind(KeyEvent.VK_SPACE, new ShootCommand(player,world));
        world.addKeyListener(input);
        world.setFocusable(true);
        world.requestFocusInWindow();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}