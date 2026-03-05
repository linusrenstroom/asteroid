package main;

import main.abstractFactory.*;
import main.conf.GameConfig;
import main.gameobject.Player;
import main.state.RunningState;
import main.strategy.LeftSideSpawnStrategy;
import main.util.Point;
import main.worldStateManagement.GameObjectContainer;
import main.worldStateManagement.SpawnManager;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private GameObjectContainer world;

    public Main() {
        super("Asteroid");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SpawnManager spawnManager = new SpawnManager(new AsteroidFactory());

        world = new GameObjectContainer(spawnManager, new RunningState());

        world.setPreferredSize(new Dimension(
                GameConfig.SCREEN_WIDTH,
                GameConfig.SCREEN_HEIGHT
        ));

        world.addObject(new Player(
                new Point(GameConfig.SCREEN_WIDTH / 2,
                        GameConfig.SCREEN_HEIGHT / 2)
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