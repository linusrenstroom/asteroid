package main.command;

import main.gameobject.Player;

public class RotateLeftCommand implements Command {

    private Player player;

    public RotateLeftCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.setRotateLeft(true);
    }

    @Override
    public void stop() {
        player.setRotateLeft(false);
    }
}