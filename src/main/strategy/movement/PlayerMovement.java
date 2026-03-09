package main.strategy.movement;

import main.Vector2D;
import main.conf.GameConfig;
import main.gameobject.GameObject;
import main.gameobject.Player;

public class PlayerMovement implements MovementStrategy{
    @Override
    public void move(GameObject object, double deltaTime) {

        Player player = (Player) object;
        // Rotation
        int rotationInput = (player.isRotatingRight() ? 1 : 0) - (player.isRotatingLeft() ? 1 : 0);
        player.setAngle(player.getAngle() + rotationInput * GameConfig.PLAYER_ROTATION_SPEED * deltaTime);

        // Thrust
        if (player.isMoving()) {
            double heading = player.getAngle() - GameConfig.PLAYER_HEADING_OFFSET_RADIANS;
            player.setVelocity(player.getVelocity().add(new Vector2D(
                    Math.cos(heading) * GameConfig.PLAYER_ACCELERATION * deltaTime,
                    Math.sin(heading) * GameConfig.PLAYER_ACCELERATION * deltaTime
            )));
        }

        // Drag
        player.setVelocity(player.getVelocity().multiply(Math.pow(GameConfig.PLAYER_DRAG_PER_SECOND, deltaTime)));

        // Position
        player.getPosition().setX(player.getPosition().getX() + player.getVelocity().x * deltaTime);
        player.getPosition().setY(player.getPosition().getY() + player.getVelocity().y * deltaTime);
    }

}
