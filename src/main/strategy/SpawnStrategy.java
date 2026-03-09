package main.strategy;

import main.factory.GameObjectFactory;
import main.gameobject.GameObject;

import java.util.List;

public interface SpawnStrategy {
    void spawn(List<GameObject> objects, GameObjectFactory factory, double gameTime);}
