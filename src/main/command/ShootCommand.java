package main.command;

import main.gameobject.Player;

public class ShootCommand implements Command {
    private final Player player;

    public ShootCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.shoot();
    }

    @Override
    public void stop() {
    } // no-op, shooting doesn't have a held state
}