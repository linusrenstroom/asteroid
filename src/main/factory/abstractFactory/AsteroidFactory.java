package main.factory.abstractFactory;

import main.Vector2D;
import main.gameobject.asteroids.Asteroid;

import main.Vector2D;
import main.factory.GameObjectFactory;


public interface AsteroidFactory extends GameObjectFactory {
    @Override
    Asteroid createGameObject(double posX, double posY, Vector2D velocity);
}
