package main.factory;

import main.Vector2D;
import main.gameobject.GameObject;
import main.util.Point;

public interface GameObjectFactory {

    public GameObject createGameObject(Point position, Vector2D velocity);
}
