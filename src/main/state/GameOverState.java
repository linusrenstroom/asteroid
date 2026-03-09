package main.state;

import main.conf.GameConfig;
import main.gameobject.Player;
import main.worldStateManagement.GameObjectContainer;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverState implements GameState{
    @Override
    public void update(double deltaTime, GameObjectContainer context) {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        g.drawString("Game Over :(", GameConfig.SCREEN_WIDTH / 2 - 150, 150);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Press ENTER to try again!", GameConfig.SCREEN_WIDTH / 2 - 120, 300);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Press ESCAPE  to exit to main menu!", GameConfig.SCREEN_WIDTH / 2 - 120, 400);
    }

    @Override
    public void keyPressed(int keyCode, GameObjectContainer context) {
        if (keyCode == KeyEvent.VK_ENTER) {
            context.reset();
            context.setGameState(new RunningState());
        }
        if(keyCode == KeyEvent.VK_ESCAPE){
            context.setGameState(new MenuState());
        }
    }

    @Override
    public void keyReleased(int keyCode, GameObjectContainer context) {

    }
}
