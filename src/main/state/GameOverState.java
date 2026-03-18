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
    public void draw(Graphics2D g, World world) {
        g.setColor(new Color(10, 0, 6));
        g.fillRect(0, 0, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(Color.WHITE);

        Font titleFont = new Font("SansSerif", Font.BOLD, 64);
        Font subFont = new Font("SansSerif", Font.PLAIN, 24);
        Font smallFont = new Font("SansSerif", Font.PLAIN, 18);

        String title = "GAME OVER";
        g.setFont(titleFont);
        FontMetrics tm = g.getFontMetrics();
        g.drawString(title, (GameConfig.SCREEN_WIDTH - tm.stringWidth(title)) / 2, 150);

        g.setFont(subFont);
        FontMetrics sm = g.getFontMetrics();
        String score = "Score: " + world.getLastRunScore();
        String high = "High score: " + world.getHighScore();
        g.drawString(score, (GameConfig.SCREEN_WIDTH - sm.stringWidth(score)) / 2, 230);
        g.drawString(high, (GameConfig.SCREEN_WIDTH - sm.stringWidth(high)) / 2, 265);

        String retry = "Press ENTER to play again";
        g.drawString(retry, (GameConfig.SCREEN_WIDTH - sm.stringWidth(retry)) / 2, 350);

        String menu = "Press ESC to return to menu";
        g.drawString(menu, (GameConfig.SCREEN_WIDTH - sm.stringWidth(menu)) / 2, 390);

        g.setFont(smallFont);
        FontMetrics xm = g.getFontMetrics();
        String hint = "Tip: ESC pauses during gameplay";
        g.drawString(hint, (GameConfig.SCREEN_WIDTH - xm.stringWidth(hint)) / 2, 520);
    }

    @Override
    public Map<Integer, Command> getKeyBindings(World world, Consumer<GameState> changeState) {
        Map<Integer, Command> bindings = new HashMap<>();
        bindings.put(KeyEvent.VK_ENTER, new ChangeStateCommand(
                () -> { world.reset(); return new RunningState(); }, changeState
        ));
        bindings.put(KeyEvent.VK_ESCAPE, new ChangeStateCommand(MenuState::new, changeState));
        return bindings;
    }

}
