package main.input.command;

import main.gameobject.Player;

public class ShootCommand implements Command {
    private Player player;

    public ShootCommand(Player player) {
        this.player = player;
    }
    @Override
    public void execute() {
        player.shoot();
    }
}
