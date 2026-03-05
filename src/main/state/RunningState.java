package main.state;

import main.gameobject.GameObject;
import main.gameobject.Player;
import main.worldStateManagement.GameObjectContainer;

import java.awt.*;

public class RunningState implements GameState{
    @Override
    public void update(double deltaTime, GameObjectContainer context) {

        context.getSpawnManager().update(deltaTime, context.getObjects());
        context.updateObjects(deltaTime);
    }

    @Override
    public void draw(Graphics2D g) {
//Kanske rita ut score
    }

    @Override
    public void keyPressed(int keyCode, GameObjectContainer context) {
        Player player = findPlayer(context);
        if (player == null) {
            return;
        }

        switch (keyCode) {
            case java.awt.event.KeyEvent.VK_LEFT:
            case java.awt.event.KeyEvent.VK_A:
                player.setRotateLeft(true);
                break;
            case java.awt.event.KeyEvent.VK_RIGHT:
            case java.awt.event.KeyEvent.VK_D:
                player.setRotateRight(true);
                break;
            case java.awt.event.KeyEvent.VK_UP:
            case java.awt.event.KeyEvent.VK_W:
                player.setMove(true);
                break;
            case java.awt.event.KeyEvent.VK_SPACE:
                player.shoot();
                break;
            case java.awt.event.KeyEvent.VK_DOWN:
            case java.awt.event.KeyEvent.VK_S:
                player.stop();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(int keyCode, GameObjectContainer context) {
        Player player = findPlayer(context);
        if (player == null) {
            return;
        }

        switch (keyCode) {
            case java.awt.event.KeyEvent.VK_LEFT:
            case java.awt.event.KeyEvent.VK_A:
                player.setRotateLeft(false);
                break;
            case java.awt.event.KeyEvent.VK_RIGHT:
            case java.awt.event.KeyEvent.VK_D:
                player.setRotateRight(false);
                break;
            case java.awt.event.KeyEvent.VK_UP:
            case java.awt.event.KeyEvent.VK_W:
                player.setMove(false);
                break;
            default:
                break;
        }
    }

    private Player findPlayer(GameObjectContainer context) {
        for (GameObject gameObject : context.getObjects()) {
            if (gameObject instanceof Player) {
                return (Player) gameObject;
            }
        }
        return null;
    }
}
