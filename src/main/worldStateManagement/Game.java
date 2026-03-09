package main;

import main.gameobject.Player;
import main.observer.LifeObserver;
import main.state.PlayingState;
import main.ui.GamePanel;
import main.util.Point;
import main.worldStateManagement.GameLoop;
import main.worldStateManagement.SpawnManager;
import main.worldStateManagement.World;

import javax.swing.JFrame;

public class Game {
    public Game() {
        Player player = new Player(new Point(400, 300));
        SpawnManager spawnManager = new SpawnManager();
        World world = new World(player, spawnManager, new PlayingState());

        LifeObserver lifeObserver = new LifeObserver();
        player.addObserver(lifeObserver);

        GamePanel panel = new GamePanel(world);
        panel.addUiObserver(lifeObserver);

        GameLoop loop = new GameLoop(world, panel);
        GameFacade facade = new GameFacade(world, loop, panel);

        // wire input
        panel.addKeyListener(new InputHandler(facade));

        JFrame frame = new JFrame("Asteroids");
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        facade.startGame();
    }

    public static void main(String[] args) {
        new Game();
    }
}