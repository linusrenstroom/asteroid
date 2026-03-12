package main.factory.abstractFactory;

import main.Vector2D;
import main.gameobject.asteroids.Asteroid;

import main.Vector2D;
import main.factory.GameObjectFactory;
import main.util.Point;


public interface AsteroidFactory extends GameObjectFactory {
    @Override
    Asteroid createGameObject(Point position, Vector2D velocity);
}
