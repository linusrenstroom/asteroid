package main.strategy.movement.decorator;

import main.conf.GameConfig;
import main.gameobject.GameObject;
import main.strategy.movement.MovementStrategy;

public class WrappingMovementStrategy implements MovementStrategy {
    private final MovementStrategy inner;
    private final double margin;

    public WrappingMovementStrategy(MovementStrategy inner, double margin) {
        this.inner = inner;
        this.margin = margin;
    }

    @Override
    public void move(GameObject object, double deltaTime) {
        inner.move(object, deltaTime);

        double x = object.getPosition().getX();
        double y = object.getPosition().getY();

        if (x < -margin)                              object.getPosition().setX(GameConfig.SCREEN_WIDTH + margin);
        else if (x > GameConfig.SCREEN_WIDTH + margin) object.getPosition().setX(-margin);

        if (y < -margin)                               object.getPosition().setY(GameConfig.SCREEN_HEIGHT + margin);
        else if (y > GameConfig.SCREEN_HEIGHT + margin) object.getPosition().setY(-margin);
    }

}
