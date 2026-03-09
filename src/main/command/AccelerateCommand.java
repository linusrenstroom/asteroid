package main.command;

import main.gameobject.Player;

public class AccelerateCommand implements Command {
    private Player player;
    public AccelerateCommand(Player player) {
        this.player = player;
    }
    @Override
    public void execute() {
        player.setMove(true);
    }

    @Override
    public void stop() {
        player.setMove(false);
    }
}
