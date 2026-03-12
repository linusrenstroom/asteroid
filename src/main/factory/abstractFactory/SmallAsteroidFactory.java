package main.factory.abstractFactory;

import main.Vector2D;
import main.gameobject.asteroids.Asteroid;
import main.gameobject.asteroids.SmallAsteroid;
import main.util.Point;

public class SmallAsteroidFactory implements AsteroidFactory{
    @Override
    public Asteroid createGameObject(Point position, Vector2D velocity) {
        return new SmallAsteroid(position, velocity);
    }
}
