package main.strategy.movement;

import main.gameobject.GameObject;

public interface MovementStrategy {
    public void move(GameObject object, double deltaTime);
}
