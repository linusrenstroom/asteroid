package main.factory;

import main.Vector2D;
import main.gameobject.Bullet;
import main.gameobject.GameObject;
import main.util.Point;

public class BulletFactory implements GameObjectFactory {
    @Override
    public GameObject createGameObject(Point position, Vector2D velocity) {
        return new Bullet(position, velocity);
    }

}
