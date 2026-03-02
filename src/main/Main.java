package main;

import main.gameobject.Asteroid;

import javax.swing.*;

public class Main extends JFrame {
    GameObjectContainer world;

    public Main(){
        super("Asteroid");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        world = new GameObjectContainer();
        world.addObject(new Asteroid());

        add(world);

        setVisible(true);

    }

    public static void main(String [] args){
        SwingUtilities.invokeLater(Main::new);
    }
}
