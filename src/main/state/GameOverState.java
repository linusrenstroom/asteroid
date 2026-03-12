package main.state;

import main.command.ChangeStateCommand;
import main.command.Command;
import main.conf.GameConfig;
import main.worldStateManagement.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class GameOverState implements GameState{
    @Override
    public void update(double deltaTime, World world, Consumer<GameState> changeState) {}

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        g.drawString("Game Over :(", GameConfig.SCREEN_WIDTH / 2 - 150, 150);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Press ENTER to try again!", GameConfig.SCREEN_WIDTH / 2 - 120, 300);
        g.drawString("Press ESCAPE to exit to main menu!", GameConfig.SCREEN_WIDTH / 2 - 120, 400);
    }

    @Override
    public Map<Integer, Command> getKeyBindings(World world, Consumer<GameState> changeState) {
        Map<Integer, Command> bindings = new HashMap<>();
        bindings.put(KeyEvent.VK_ENTER, new ChangeStateCommand(
                () -> { world.reset(); return new RunningState(); }, changeState
        ));
        bindings.put(KeyEvent.VK_ESCAPE, new ChangeStateCommand(
                MenuState::new, changeState
        ));
        return bindings;
    }

}
