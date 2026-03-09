package main.command;

import main.command.Command;
import main.gameobject.GameObject;
import main.gameobject.Player;
import main.worldStateManagement.GameObjectContainer;

public class ShootCommand implements Command {
    private final Player player;
    private final GameObjectContainer context;

    public ShootCommand(Player player, GameObjectContainer context) {
        this.player = player;
        this.context = context;
    }

    @Override
    public void execute() {
        GameObject bullet = player.shoot();
        if (bullet != null) {
            context.addObject(bullet);
        }
    }

    @Override
    public void stop() {

    }
}