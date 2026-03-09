package main.strategy.movement.decorator;

import main.conf.GameConfig;
import main.gameobject.GameObject;
import main.strategy.movement.MovementStrategy;

public class DespawningMovementStrategy implements MovementStrategy {
    private final MovementStrategy inner;
    private final double margin;

    public DespawningMovementStrategy(MovementStrategy inner, double margin) {
        this.inner = inner;
        this.margin = margin;
    }

    @Override
    public void move(GameObject object, double deltaTime) {
        inner.move(object, deltaTime);

        double x = object.getPosition().getX();
        double y = object.getPosition().getY();

        if (x < -margin || x > GameConfig.SCREEN_WIDTH + margin ||
                y < -margin || y > GameConfig.SCREEN_HEIGHT + margin) {
            object.destroy();
        }
    }
}

