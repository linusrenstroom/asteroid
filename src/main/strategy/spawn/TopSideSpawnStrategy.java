package main.strategy.spawn;

import main.factory.GameObjectFactory;
import main.conf.GameConfig;
import main.gameobject.GameObject;
import main.Vector2D;

import java.util.List;
import java.util.Random;

public class TopSideSpawnStrategy implements SpawnStrategy {
    private final Random random = new Random();


    @Override
    public void spawn(List<GameObject> objects,GameObjectFactory factory, double gameTime) {
        double spawnX = random.nextDouble() * GameConfig.SCREEN_WIDTH;
        double spawnY = -GameConfig.SPAWN_OFFSCREEN_MARGIN;
        double targetX = GameConfig.SCREEN_WIDTH * GameConfig.SPAWN_TARGET_X_OFFSET_FACTOR
                + (random.nextDouble() * GameConfig.SCREEN_WIDTH * GameConfig.SPAWN_TARGET_X_RANGE_FACTOR);
        double targetY = GameConfig.SCREEN_HEIGHT * GameConfig.SPAWN_TARGET_Y_BASE_FACTOR
                + (random.nextDouble() * GameConfig.SCREEN_HEIGHT * GameConfig.SPAWN_TARGET_Y_RANGE_FACTOR);
        double speed = GameConfig.SPAWN_ASTEROID_BASE_SPEED
                + gameTime * GameConfig.SPAWN_ASTEROID_SPEED_INCREASE_PER_SECOND;

        double deltaX = targetX - spawnX;
        double deltaY = targetY - spawnY;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        Vector2D velocity = new Vector2D((deltaX / distance) * speed, (deltaY / distance) * speed);

        objects.add(factory.createGameObject(spawnX, spawnY, velocity));
    }
}