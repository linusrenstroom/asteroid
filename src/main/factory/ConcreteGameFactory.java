package main.factory;

import main.conf.GameConfig;
import main.factory.BulletFactory;
import main.gameobject.Player;
import main.state.MenuState;
import main.util.Point;
import main.worldStateManagement.Game;
import main.worldStateManagement.SpawnManager;

public class ConcreteGameFactory {
    public static Player createPlayer() {
        return new Player(new Point(
                GameConfig.SCREEN_WIDTH * GameConfig.PLAYER_START_X_RATIO,
                GameConfig.SCREEN_HEIGHT * GameConfig.PLAYER_START_Y_RATIO
        ));
    }

    public static SpawnManager createSpawnManager() {
        return new SpawnManager();
    }

    public static Game createGame() {
        return new Game(createPlayer(), createSpawnManager(), new MenuState(), new BulletFactory());
    }
}
