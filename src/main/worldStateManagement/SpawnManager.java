package main.worldStateManagement;

import main.Vector2D;
import main.conf.GameConfig;
import main.factory.EnemyShipFactory;
import main.factory.abstractFactory.*;
import main.gameobject.GameObject;
import main.observer.Observer;
import main.strategy.spawn.LeftSideSpawnStrategy;
import main.strategy.spawn.RightSideSpawnStrategy;
import main.strategy.spawn.SpawnStrategy;
import main.strategy.spawn.TopSideSpawnStrategy;
import main.util.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpawnManager {
    private final SpawnStrategy[] strategies;
    private final AsteroidFactory[] asteroidFactories;
    private final Random random = new Random();
    private final List<Observer> observers = new ArrayList<>();
    private final EnemyShipFactory shipFactory;

    private double accumulatedTime = 0;
    private double totalGameTime = 0;
    private double enemySpawnTimer = 0;
    private final double enemySpawnInterval = 10.0;

    public SpawnManager() {
        this.asteroidFactories = new AsteroidFactory[] {
                new SmallAsteroidFactory(),
                new LargeAsteroidFactory(),
                new StandardAsteroidFactory()
        };
        this.shipFactory = new EnemyShipFactory();
        this.strategies = new SpawnStrategy[] {
                new LeftSideSpawnStrategy(),
                new RightSideSpawnStrategy(),
                new TopSideSpawnStrategy()
        };
    }

    public void update(double deltaTime, World world) {
        totalGameTime += deltaTime;
        accumulatedTime += deltaTime;
        enemySpawnTimer += deltaTime;

        if (accumulatedTime >= GameConfig.BASE_SPAWN_RATE_SECONDS) {
            accumulatedTime = 0;
            spawnAsteroid(world);
        }

        if (enemySpawnTimer >= enemySpawnInterval) {
            enemySpawnTimer = 0;
            spawnEnemyShip(world);
        }
    }

    private void spawnAsteroid(World world) {
        SpawnStrategy strategy = strategies[random.nextInt(strategies.length)];
        AsteroidFactory factory = asteroidFactories[random.nextInt(asteroidFactories.length)];

        List<GameObject> spawned = strategy.spawn(factory, totalGameTime);
        for (GameObject obj : spawned) {
            observers.forEach(obj::addObserver);
            world.addObject(obj);
        }
    }

    private void spawnEnemyShip(World world) {
        SpawnStrategy strategy = strategies[random.nextInt(strategies.length)];

        List<GameObject> spawned = strategy.spawn(shipFactory, totalGameTime);
        for (GameObject ship : spawned) {
            observers.forEach(ship::addObserver);
            world.addObject(ship);
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }
}