package main.strategy.movement;

import main.conf.GameConfig;
import main.gameobject.EnemyShip;
import main.gameobject.GameObject;


public class EnemyPatrolMovement implements MovementStrategy {
    // Seconds per leg of the L-shaped path.
    private static final double LEG_DURATION_SECONDS = 1.2;

    @Override
    public void move(GameObject object, double deltaTime) {
        double speed = GameConfig.ENEMY_SHIP_PATROL_SPEED;
        boolean verticalLeg = false;
        double phaseTime = 0.0;
        if (object instanceof EnemyShip enemy) {
            verticalLeg = enemy.isPatrolVerticalLeg();
            phaseTime = enemy.getPatrolPhaseTime() + deltaTime;
            if (phaseTime >= LEG_DURATION_SECONDS) {
                phaseTime = 0.0;
                verticalLeg = !verticalLeg;
            }
            enemy.setPatrolPhaseTime(phaseTime);
            enemy.setPatrolVerticalLeg(verticalLeg);
        }

        double x = object.getPosition().getX() + (verticalLeg ? 0.0 : speed * deltaTime);
        double y = object.getPosition().getY() + (verticalLeg ? speed * deltaTime : 0.0);

        // Wrap around instead of bouncing (more "spacey" and avoids sticking on edges).
        if (x < 0) x = GameConfig.SCREEN_WIDTH;
        else if (x > GameConfig.SCREEN_WIDTH) x = 0;

        if (y < 0) y = GameConfig.SCREEN_HEIGHT;
        else if (y > GameConfig.SCREEN_HEIGHT) y = 0;

        object.getPosition().setX(x);
        object.getPosition().setY(y);
    }
}
