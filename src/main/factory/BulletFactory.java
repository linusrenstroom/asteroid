package main.factory;

import main.Vector2D;
import main.gameobject.GameObject;
import main.gameobject.bullet.EnemyBullet;
import main.gameobject.bullet.PlayerBullet;
import main.util.Point;

public class BulletFactory implements GameObjectFactory {
    @Override
    public GameObject createGameObject(Point position, Vector2D velocity) {
        return new PlayerBullet(position, velocity);
    }

    public GameObject createEnemyBullet(Point position, Vector2D velocity) {
        return new EnemyBullet(position, velocity);
    }
}
