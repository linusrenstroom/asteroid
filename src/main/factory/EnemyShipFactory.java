package main.factory;

import main.Vector2D;
import main.conf.GameConfig;
import main.gameobject.EnemyShip;
import main.gameobject.GameObject;
import main.util.Point;

public class EnemyShipFactory implements GameObjectFactory {
    @Override
    public GameObject createGameObject(Point position, Vector2D direction) {
        // EnemyShip movement is controlled by its patrol strategy; spawn direction is ignored.
        return new EnemyShip(position, new Vector2D(GameConfig.ENEMY_SHIP_PATROL_SPEED, 0));
    }
}