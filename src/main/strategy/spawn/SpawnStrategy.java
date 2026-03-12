package main.strategy.spawn;

import main.factory.GameObjectFactory;
import main.gameobject.GameObject;

import java.util.List;

public interface SpawnStrategy {
    List<GameObject> spawn(GameObjectFactory factory, double gameTime);}
