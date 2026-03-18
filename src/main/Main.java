package main;

import main.conf.GameConfig;
import main.facade.AsteroidsGame;

import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        super(GameConfig.WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Backwards-compatible entry point; facade owns real setup.
        AsteroidsGame game = new AsteroidsGame();
        game.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}