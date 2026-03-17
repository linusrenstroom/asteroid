package main.factory;

import main.Vector2D;
import main.gameobject.Bullet;
import main.gameobject.GameObject;
import main.util.Point;

import java.awt.Color;

public class BulletFactory implements GameObjectFactory {
    @Override
    public GameObject createGameObject(Point position, Vector2D velocity) {
        return new Bullet(position, velocity);
    }

    public GameObject createEnemyBullet(Point position, Vector2D velocity) {
        return new Bullet(position, velocity, Color.GREEN);
    }
}
