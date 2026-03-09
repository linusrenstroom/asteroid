package main.factory;

import main.Vector2D;
import main.gameobject.EnemyShip;
import main.gameobject.GameObject;

public class EnemyShipFactory implements GameObjectFactory {
    @Override
    public GameObject createGameObject(double posX, double posY, Vector2D direction) {
        return new EnemyShip(posX, posY, direction.multiply(2));
    }
}