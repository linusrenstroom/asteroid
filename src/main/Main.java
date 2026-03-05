package main;

import main.abstractFactory.AsteroidFactory;
import main.abstractFactory.BulletFactory;
import main.abstractFactory.EnemyShipFactory;
import main.conf.GameConfig;
import main.gameobject.Asteroid;
import main.gameobject.Player;
import main.util.Point;
import main.worldStateManagement.GameObjectContainer;
import main.worldStateManagement.SpawnManager;

import javax.swing.*;

public class Main extends JFrame {
    GameObjectContainer world;

    public Main(){
        super("Asteroid");
        setSize(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        world = new GameObjectContainer(new SpawnManager(new AsteroidFactory(),new EnemyShipFactory(),new BulletFactory()));
        world.addObject(new Player(new Point(GameConfig.SCREEN_WIDTH/2, GameConfig.SCREEN_HEIGHT/2)));
        add(world);


        setVisible(true);

    }

    public static void main(String [] args){
        SwingUtilities.invokeLater(Main::new);
    }
}
