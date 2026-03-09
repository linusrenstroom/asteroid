package main.strategy.movement;

import main.gameobject.GameObject;

public class LinearMovement implements  MovementStrategy{
    @Override
    public void move(GameObject object, double deltaTime) {
        object.getPosition().setX(object.getPosition().getX() + object.getVelocity().x * deltaTime);
        object.getPosition().setY(object.getPosition().getY() + object.getVelocity().y * deltaTime);
    }
}
