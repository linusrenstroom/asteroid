package main.factory.abstractFactory;

import main.Vector2D;
import main.gameobject.GameObject;
import main.gameobject.asteroids.Asteroid;
import main.gameobject.asteroids.LargeAsteroid;

public class LargeAsteroidFactory implements AsteroidFactory {
    @Override
    public Asteroid createGameObject(double posX, double posY, Vector2D velocity) {
        return new LargeAsteroid(posX, posY, velocity);
    }
}
