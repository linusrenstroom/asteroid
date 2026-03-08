package main.command;

import main.Vector2D;
import main.command.Command;
import main.factory.GameObjectFactory;
import main.gameobject.Player;
import main.worldStateManagement.GameObjectContainer;

public class ShootCommand implements Command {
    private final Player player;
    private final GameObjectFactory bulletFactory;

    public ShootCommand(Player player, GameObjectFactory bulletFactory) {
        this.player = player;
        this.bulletFactory = bulletFactory;
    }

    @Override
    public void execute() {
        player.shoot();
    }

    @Override
    public void stop() {}
}