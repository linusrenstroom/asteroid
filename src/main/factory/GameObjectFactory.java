package main.factory;

import main.Vector2D;
import main.gameobject.GameObject;

public interface GameObjectFactory {

    public GameObject createGameObject(double posX, double posY, Vector2D velocity);
}
