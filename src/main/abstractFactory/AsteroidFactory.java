package main.abstractFactory;

import main.Vector2D;
import main.gameobject.Asteroid;
import main.gameobject.GameObject;

public class AsteroidFactory implements GameObjectFactory {


    @Override
    public GameObject createGameObject(double posX, double posY, Vector2D velocity) {
        return new Asteroid(20, posX, posY, velocity.x, velocity.y);
    }
}
