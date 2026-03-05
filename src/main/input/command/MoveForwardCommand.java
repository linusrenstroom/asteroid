package main.input.command;

import main.gameobject.Player;

public class MoveForwardCommand implements Command {

    private final Player player;
    private final double deltaTime;

    public MoveForwardCommand(Player player, double deltaTime) {
        this.player = player;
        this.deltaTime = deltaTime;
    }

    @Override
    public void execute() {
        player.accelerate(deltaTime);
    }
}