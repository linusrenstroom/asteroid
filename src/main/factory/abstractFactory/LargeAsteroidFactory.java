package main.factory.abstractFactory;

import main.Vector2D;
import main.gameobject.GameObject;
import main.gameobject.asteroids.Asteroid;
import main.gameobject.asteroids.LargeAsteroid;
import main.util.Point;

public class LargeAsteroidFactory implements AsteroidFactory {
    @Override
    public Asteroid createGameObject(Point position, Vector2D velocity) {
        return new LargeAsteroid(position, velocity);
    }
}
