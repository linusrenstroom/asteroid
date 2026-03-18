package main;

import main.conf.GameConfig;

import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        super(GameConfig.WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Backwards-compatible entry point; facade owns real setup.
        new AsteroidsGame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}