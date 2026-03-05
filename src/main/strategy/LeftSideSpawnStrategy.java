package main.strategy;



import main.abstractFactory.GameObjectFactory;
import main.conf.GameConfig;
import main.gameobject.GameObject;
import main.Vector2D;

import java.util.List;
import java.util.Random;

public class LeftSideSpawnStrategy implements SpawnStrategy {

    private final GameObjectFactory asteroidFactory;
    private final Random random = new Random();

    public LeftSideSpawnStrategy(GameObjectFactory asteroidFactory) {
        this.asteroidFactory = asteroidFactory;
    }

    @Override
    public void spawn(List<GameObject> objects, double gameTime) {
        double spawnX = -50;
        double spawnY = random.nextDouble() * GameConfig.SCREEN_HEIGHT;

        double targetX = GameConfig.SCREEN_WIDTH / 2.0 + (random.nextDouble() * GameConfig.SCREEN_WIDTH / 4.0);
        double targetY = GameConfig.SCREEN_HEIGHT / 4.0 + (random.nextDouble() * GameConfig.SCREEN_HEIGHT / 2.0);

        double speed = 100 + gameTime * 2;

        double deltaX = targetX - spawnX;
        double deltaY = targetY - spawnY;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        Vector2D velocity = new Vector2D((deltaX / distance) * speed, (deltaY / distance) * speed);

        objects.add(asteroidFactory.createGameObject(spawnX, spawnY, velocity));
    }
}