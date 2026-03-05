package main;

import main.abstractFactory.*;
import main.conf.GameConfig;
import main.gameobject.Player;
import main.state.RunningState;
import main.util.Point;
import main.worldStateManagement.GameObjectContainer;
import main.worldStateManagement.SpawnManager;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private GameObjectContainer world;

    public Main() {
        super(GameConfig.WINDOW_TITLE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SpawnManager spawnManager = new SpawnManager(new AsteroidFactory());

        world = new GameObjectContainer(spawnManager, new RunningState());

        world.setPreferredSize(new Dimension(
                GameConfig.SCREEN_WIDTH,
                GameConfig.SCREEN_HEIGHT
        ));

        world.addObject(new Player(
                new Point(
                        GameConfig.SCREEN_WIDTH * GameConfig.PLAYER_START_X_RATIO,
                        GameConfig.SCREEN_HEIGHT * GameConfig.PLAYER_START_Y_RATIO
                )
        ));

        add(world);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}