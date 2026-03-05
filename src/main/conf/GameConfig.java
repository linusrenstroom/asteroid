package main.conf;

import main.input.command.*;
import main.gameobject.Player;

import java.util.HashMap;
import java.util.Map;

public class GameConfig {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;

    public static final Map<Integer, Command> keyPressedCommands = new HashMap<>();
    public static final Map<Integer, Command> keyReleasedCommands = new HashMap<>();

    public static void initPlayerCommands(Player player, double deltaTime) {
        keyPressedCommands.put(87, new MoveForwardCommand(player, deltaTime)); // W
        keyPressedCommands.put(65, new RotateLeftCommand(player, 0.1));         // A
        keyPressedCommands.put(68, new RotateRightCommand(player, 0.1));        // D
        keyPressedCommands.put(32, new ShootCommand(player));                    // SPACE

        keyReleasedCommands.put(87, () -> player.stopAcceleration());          // W released
    }
}