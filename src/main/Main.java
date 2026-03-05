package main;

import main.abstractFactory.AsteroidFactory;
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
        super("Asteroid");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SpawnManager spawnManager = new SpawnManager(new AsteroidFactory());
        world = new GameObjectContainer(spawnManager, null);

        Player player = new Player(
                new Point(GameConfig.SCREEN_WIDTH / 2, GameConfig.SCREEN_HEIGHT / 2),
                world
        );

        RunningState runningState = new RunningState(player, 0.016);
        world.setGameState(runningState);
        world.addObject(player);

        world.setPreferredSize(new Dimension(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
        add(world);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}