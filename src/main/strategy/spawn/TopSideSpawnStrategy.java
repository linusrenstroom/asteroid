package main.strategy.spawn;

import main.conf.GameConfig;
import main.util.Point;

import java.util.Random;

public class TopSideSpawnStrategy implements SpawnStrategy {
    private final Random random = new Random();


    @Override
    public Point spawnPosition() {
        double spawnX = random.nextDouble() * GameConfig.SCREEN_WIDTH;
        double spawnY = -GameConfig.SPAWN_OFFSCREEN_MARGIN;
        return new Point(spawnX, spawnY);
    }
}