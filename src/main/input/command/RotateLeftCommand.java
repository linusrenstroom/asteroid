package main.input.command;

import main.gameobject.Player;

public class RotateLeftCommand implements Command {

    private final Player player;
    private final double deltaAngle;

    public RotateLeftCommand(Player player, double deltaAngle) {
        this.player = player;
        this.deltaAngle = deltaAngle;
    }

    @Override
    public void execute() {
        player.rotate(deltaAngle);
    }
}