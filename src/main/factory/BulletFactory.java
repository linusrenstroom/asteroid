package main.factory;

import main.Vector2D;
import main.gameobject.Bullet;
import main.gameobject.GameObject;
import main.util.Point;

public class BulletFactory implements GameObjectFactory {
    @Override
    public GameObject createGameObject(double posX, double posY, Vector2D velocity) {
        return new Bullet(new Point(posX,posY), velocity);
    }
//    @Override
//    public GameObject createGameObject(double posX, double posY, Vector2D velocity) {
//        return new Bullet(n velocity);
//    }
}
