package main.strategy.spawn;

import main.conf.GameConfig;
import main.util.Point;

import java.util.Random;

public class RightSideSpawnStrategy implements SpawnStrategy {

    private final Random random = new Random();
    @Override
    public Point spawnPosition() {
        double spawnX = GameConfig.SCREEN_WIDTH + GameConfig.SPAWN_OFFSCREEN_MARGIN;
        double spawnY = random.nextDouble() * GameConfig.SCREEN_HEIGHT;
        return new Point(spawnX, spawnY);
    }
}
