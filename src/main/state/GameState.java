package main.state;

import main.worldStateManagement.World;

import java.awt.*;
import java.util.function.Consumer;

public interface GameState {
    void update(double deltaTime, World world, Consumer<GameState> changeState);
    void draw(Graphics2D g);
    void keyPressed(int keyCode, World world, Consumer<GameState> changeState);
    void keyReleased(int keyCode, World world, Consumer<GameState> changeState);

}
