package main.strategy.spawn;

import main.gameobject.GameObject;

import java.util.List;

public interface SpawnStrategy {
    void spawn(List<GameObject> objects, double gameTime);
}
