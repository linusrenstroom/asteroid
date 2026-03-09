package main.state;
import main.command.ShootCommand;
import main.gameobject.GameObject;
import main.worldStateManagement.GameObjectContainer;
import java.awt.*;
import java.awt.event.KeyEvent;

public class RunningState implements GameState {

    @Override
    public void update(double deltaTime, GameObjectContainer context) {
        context.checkCollisions();
        if(context.getPlayer().isDead()){
            context.setGameState(new GameOverState());
        }
        context.getSpawnManager().update(deltaTime, context.getObjects());
        context.updateObjects(deltaTime);
    }

    @Override
    public void draw(Graphics2D g) {
    }

    @Override
    public void keyPressed(int keyCode, GameObjectContainer context) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            context.setGameState(new PausedState(this));
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            new ShootCommand(context.getPlayer(), context).execute();
        }
    }

    @Override
    public void keyReleased(int keyCode, GameObjectContainer context) {

    }
}