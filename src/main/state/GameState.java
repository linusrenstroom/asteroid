package main.state;

import main.command.Command;
import main.worldStateManagement.World;

import java.awt.*;
import java.util.Map;
import java.util.function.Consumer;

public interface GameState {
    void update(double deltaTime, World world, Consumer<GameState> changeState);
    void draw(Graphics2D g);
    Map<Integer, Command> getKeyBindings(World world, Consumer<GameState> changeState);

}
