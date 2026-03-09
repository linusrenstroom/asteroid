package main.factory.abstractFactory;

import main.Vector2D;
import main.gameobject.asteroids.Asteroid;
import main.gameobject.asteroids.StandardAsteroid;

public class StandardAsteroidFactory implements AsteroidFactory {
    @Override
    public Asteroid createGameObject(double posX, double posY, Vector2D velocity) {
        return new StandardAsteroid(posX, posY, velocity);
    }
}
