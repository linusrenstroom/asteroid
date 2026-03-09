// MenuState.java
package main.state;

import main.conf.GameConfig;
import main.worldStateManagement.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

public class MenuState implements GameState {

    @Override
    public void update(double deltaTime, World world, Consumer<GameState> changeState) {}

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        g.drawString("ASTEROIDS", GameConfig.SCREEN_WIDTH / 2 - 150, 150);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Press ENTER to Start", GameConfig.SCREEN_WIDTH / 2 - 120, 300);
        g.drawString("Press ESC to Quit", GameConfig.SCREEN_WIDTH / 2 - 100, 350);
    }

    @Override
    public void keyPressed(int keyCode, World world, Consumer<GameState> changeState) {
        if (keyCode == KeyEvent.VK_ENTER) {
            world.reset();
            changeState.accept(new RunningState());
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(int keyCode, World world, Consumer<GameState> changeState) {}
}