package main.factory.abstractFactory;

import main.Vector2D;
import main.gameobject.asteroids.Asteroid;
import main.gameobject.asteroids.StandardAsteroid;
import main.util.Point;

public class StandardAsteroidFactory implements AsteroidFactory {
    @Override
    public Asteroid createGameObject(Point position, Vector2D velocity) {
        return new StandardAsteroid(position, velocity);
    }
}
