package main.worldStateManagement;

import main.conf.GameConfig;
import main.factory.abstractFactory.*;
import main.gameobject.GameObject;
import main.strategy.spawn.LeftSideSpawnStrategy;
import main.strategy.spawn.RightSideSpawnStrategy;
import main.strategy.spawn.SpawnStrategy;
import main.strategy.spawn.TopSideSpawnStrategy;

import java.util.List;
import java.util.Random;

public class SpawnManager {
    private final SpawnStrategy[] strategies;
    private final AsteroidFactory[] asteroidFactories;
    private final Random random = new Random();

    private double accumulatedTime = 0;
    private double totalGameTime = 0;

    public SpawnManager() {
        this.asteroidFactories = new AsteroidFactory[] {
                new SmallAsteroidFactory(),
                new LargeAsteroidFactory(),
                new StandardAsteroidFactory()
        };

        this.strategies = new SpawnStrategy[] {
                new LeftSideSpawnStrategy(),
                new RightSideSpawnStrategy(),
                new TopSideSpawnStrategy()
        };
    }

    public void update(double deltaTime, List<GameObject> objects) {
        totalGameTime += deltaTime;
        accumulatedTime += deltaTime;

        if (accumulatedTime >= GameConfig.BASE_SPAWN_RATE_SECONDS) {
            accumulatedTime = 0;

            SpawnStrategy strategy = strategies[random.nextInt(strategies.length)];
            AsteroidFactory factory = asteroidFactories[random.nextInt(asteroidFactories.length)];

            strategy.spawn(objects, factory, totalGameTime);
        }
    }
}