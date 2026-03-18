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

        Point spawnPos = strategy.spawnPosition();
        Point targetPos = randomTargetPosition();
        Vector2D velocity = velocityTowards(spawnPos, targetPos, asteroidSpeed(totalGameTime));

        GameObject asteroid = factory.createGameObject(spawnPos, velocity);
        observers.forEach(asteroid::addObserver);
        world.addObject(asteroid);
    }

    private void spawnEnemyShip(World world) {
        SpawnStrategy strategy = strategies[random.nextInt(strategies.length)];

        Point spawnPos = strategy.spawnPosition();
        // EnemyShipFactory ignores the velocity; its movement strategy controls motion.
        GameObject ship = shipFactory.createGameObject(spawnPos, new Vector2D(0, 0));
        observers.forEach(ship::addObserver);
        world.addObject(ship);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void reset() {
        accumulatedTime = 0;
        totalGameTime = 0;
        enemySpawnTimer = 0;
    }

    private double asteroidSpeed(double gameTime) {
        return GameConfig.SPAWN_ASTEROID_BASE_SPEED
                + gameTime * GameConfig.SPAWN_ASTEROID_SPEED_INCREASE_PER_SECOND;
    }

    private Point randomTargetPosition() {
        double targetX = GameConfig.SCREEN_WIDTH * GameConfig.SPAWN_TARGET_X_OFFSET_FACTOR
                + (random.nextDouble() * GameConfig.SCREEN_WIDTH * GameConfig.SPAWN_TARGET_X_RANGE_FACTOR);
        double targetY = GameConfig.SCREEN_HEIGHT * GameConfig.SPAWN_TARGET_Y_BASE_FACTOR
                + (random.nextDouble() * GameConfig.SCREEN_HEIGHT * GameConfig.SPAWN_TARGET_Y_RANGE_FACTOR);
        return new Point(targetX, targetY);
    }

    private Vector2D velocityTowards(Point from, Point to, double speed) {
        double deltaX = to.getX() - from.getX();
        double deltaY = to.getY() - from.getY();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        if (distance == 0) return new Vector2D(speed, 0);
        return new Vector2D((deltaX / distance) * speed, (deltaY / distance) * speed);
    }
}