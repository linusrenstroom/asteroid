// MenuState.java
package main.state;

import main.command.ChangeStateCommand;
import main.command.Command;
import main.command.QuitCommand;
import main.conf.GameConfig;
import main.worldStateManagement.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MenuState implements GameState {

    @Override
    public void update(double deltaTime, World world, Consumer<GameState> changeState) {}

    @Override
    public void draw(Graphics2D g, World world) {
        g.setColor(new Color(5, 6, 12));
        g.fillRect(0, 0, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(Color.WHITE);

        Font titleFont = new Font("SansSerif", Font.BOLD, 72);
        Font subFont = new Font("SansSerif", Font.PLAIN, 22);
        Font smallFont = new Font("SansSerif", Font.PLAIN, 16);

        String title = "ASTEROIDS";
        g.setFont(titleFont);
        FontMetrics tm = g.getFontMetrics();
        int titleX = (GameConfig.SCREEN_WIDTH - tm.stringWidth(title)) / 2;
        int titleY = 160;
        g.drawString(title, titleX, titleY);

        g.setFont(subFont);
        String hs = "High score: " + world.getHighScore();
        FontMetrics sm = g.getFontMetrics();
        g.drawString(hs, (GameConfig.SCREEN_WIDTH - sm.stringWidth(hs)) / 2, titleY + 50);

        String start = "Press ENTER to start";
        g.drawString(start, (GameConfig.SCREEN_WIDTH - sm.stringWidth(start)) / 2, 320);

        String quit = "Press ESC to quit";
        g.drawString(quit, (GameConfig.SCREEN_WIDTH - sm.stringWidth(quit)) / 2, 360);

        g.setFont(smallFont);
        FontMetrics xm = g.getFontMetrics();
        String controls = "Controls: W accelerate, A/D rotate, SPACE shoot, ESC pause";
        g.drawString(controls, (GameConfig.SCREEN_WIDTH - xm.stringWidth(controls)) / 2, 520);
    }

    @Override
    public Map<Integer, Command> getKeyBindings(World world, Consumer<GameState> changeState) {
        Map<Integer, Command> bindings = new HashMap<>();
        bindings.put(KeyEvent.VK_ENTER, new ChangeStateCommand(
                () -> { world.reset(); return new RunningState(); }, changeState
        ));
        bindings.put(KeyEvent.VK_ESCAPE, new QuitCommand());
        return bindings;
    }
}