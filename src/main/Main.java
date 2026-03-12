package main;

import main.conf.GameConfig;
import main.factory.ConcreteGameFactory;
import main.worldStateManagement.Game;

import javax.swing.*;
import java.awt.Dimension;

public class Main extends JFrame {

    public Main() {
        super(GameConfig.WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Game game = ConcreteGameFactory.createGame();
        game.setPreferredSize(new Dimension(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));

        add(game);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        game.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}