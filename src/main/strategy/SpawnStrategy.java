package main.strategy;

import main.gameobject.GameObject;

import java.util.List;

public interface SpawnStrategy {
    void spawn(List<GameObject> objects, double gameTime);
}
