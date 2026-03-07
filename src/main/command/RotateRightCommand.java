package main.command;

import main.gameobject.Player;

public class RotateRightCommand implements Command {

    private Player player;

    public RotateRightCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.setRotateRight(true);
    }

    @Override
    public void stop() {
        player.setRotateRight(false);
    }
}
