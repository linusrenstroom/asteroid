package main.worldStateManagement;

import main.abstractFactory.BulletFactory;
import main.abstractFactory.GameObjectFactory;
import main.conf.GameConfig;
import main.gameobject.GameObject;
import main.Vector2D;
import java.util.List;
import java.util.Random;

public class SpawnManager {
    private final GameObjectFactory asteroidFactory;
    private final GameObjectFactory enemyFactory;
    private final BulletFactory bulletFactory;
    private Random random = new Random();

    private double gameTime = 0;
    private double nextSpawnTime = 0;
    private final double BASE_SPAWN_RATE = 1.0;

    public SpawnManager(GameObjectFactory asteroidFactory, GameObjectFactory enemyFactory, BulletFactory bulletFactory) {
        this.asteroidFactory = asteroidFactory;
        this.enemyFactory = enemyFactory;
        this.bulletFactory = bulletFactory;
    }

    public void update(double deltaTime, List<GameObject> objects) {
        gameTime += deltaTime;
        nextSpawnTime += deltaTime;
        if (nextSpawnTime >= getCurrentSpawnInterval()) {
            nextSpawnTime = 0;
            spawnRandomObject(objects);
        }
    }

    //TODO refaktorera så det inte blir If-satser. Använd strategy eller state!
    private void spawnRandomObject(List<GameObject> objects) {
        int roll = random.nextInt(3);
        double speed = 100 + (gameTime * 2.0);
        double spawnX, spawnY;

        double targetX = GameConfig.SCREEN_WIDTH / 4.0 + (random.nextDouble() * GameConfig.SCREEN_WIDTH / 2.0);
        double targetY = GameConfig.SCREEN_HEIGHT / 4.0 + (random.nextDouble() * GameConfig.SCREEN_HEIGHT / 2.0);

        if (roll == 0) { // Spawn Left
            spawnX = -50;
            spawnY = random.nextDouble() * GameConfig.SCREEN_HEIGHT;
        } else if (roll == 1) { // Spawn Right
            spawnX = GameConfig.SCREEN_WIDTH + 50;
            spawnY = random.nextDouble() * GameConfig.SCREEN_HEIGHT;
        } else { // Spawn Top
            spawnX = random.nextDouble() * GameConfig.SCREEN_WIDTH;
            spawnY = -50;
        }
        double deltaX = targetX - spawnX;
        double deltaY = targetY - spawnY;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        Vector2D velocity = new Vector2D((deltaX / distance) * speed, (deltaY / distance) * speed);

        objects.add(asteroidFactory.createGameObject(spawnX, spawnY, velocity));
    }

    private double getCurrentSpawnInterval() {
        return BASE_SPAWN_RATE;
    }
}
