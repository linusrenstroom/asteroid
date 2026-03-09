package main.worldStateManagement;

import main.factory.GameObjectFactory;
import main.conf.GameConfig;
import main.gameobject.GameObject;
import main.strategy.spawn.LeftSideSpawnStrategy;
import main.strategy.spawn.RightSideSpawnStrategy;
import main.strategy.spawn.SpawnStrategy;
import main.strategy.spawn.TopSideSpawnStrategy;

import java.util.List;
import java.util.Random;

public class SpawnManager {
    private final SpawnStrategy[] strategies;
    private SpawnStrategy spawnStrategy;
    private final Random random = new Random();
    private double gameTime = 0;
    private double nextSpawnTime = 0;

    public SpawnManager(GameObjectFactory factory) {
        spawnStrategy = new LeftSideSpawnStrategy(factory);
        this.strategies = new SpawnStrategy[] {
                new LeftSideSpawnStrategy(factory),
                new RightSideSpawnStrategy(factory),
                new TopSideSpawnStrategy(factory)
        };
    }

    public void setSpawnStrategy(SpawnStrategy strategy) {
        this.spawnStrategy = strategy;
    }

    public void update(double deltaTime, List<GameObject> objects) {
        gameTime += deltaTime;
        nextSpawnTime += deltaTime;
        if (nextSpawnTime >= GameConfig.BASE_SPAWN_RATE_SECONDS) {
            nextSpawnTime = 0;
            spawnStrategy = strategies[random.nextInt(strategies.length)];
            spawnStrategy.spawn(objects, gameTime);
        }
    }
}