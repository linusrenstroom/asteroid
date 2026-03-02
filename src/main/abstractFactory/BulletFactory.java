package main.abstractFactory;

import main.Vector2D;
import main.gameobject.Bullet;
import main.gameobject.GameObject;

public class BulletFactory implements GameObjectFactory {
    @Override
    public GameObject createGameObject(double posX, double posY, Vector2D velocity) {
        return new Bullet(posX, posY, velocity);
    }
}
