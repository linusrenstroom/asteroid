package main.factory;

import main.Vector2D;
import main.gameobject.EnemyShip;
import main.gameobject.GameObject;
import main.util.Point;

public class EnemyShipFactory implements GameObjectFactory {
    @Override
    public GameObject createGameObject(Point position, Vector2D direction) {
        return new EnemyShip(position, direction.multiply(2));
    }
}