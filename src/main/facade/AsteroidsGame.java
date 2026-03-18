package main.facade;

import main.SoundManager;
import main.conf.GameConfig;
import main.factory.BulletFactory;
import main.gameobject.Player;
import main.state.MenuState;
import main.util.Point;
import main.worldStateManagement.Game;
import main.worldStateManagement.SpawnManager;

import javax.swing.JFrame;
import java.awt.Dimension;


public class AsteroidsGame implements GameFacade {
    private final JFrame frame;
    private final Game game;
    private final SoundManager soundManager;

    public AsteroidsGame() {
        this(new SoundManager());
    }

    public AsteroidsGame(SoundManager soundManager) {
        this.soundManager = soundManager;

        this.frame = new JFrame(GameConfig.WINDOW_TITLE);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Player player = new Player(new Point(
                GameConfig.SCREEN_WIDTH * GameConfig.PLAYER_START_X_RATIO,
                GameConfig.SCREEN_HEIGHT * GameConfig.PLAYER_START_Y_RATIO
        ));
        SpawnManager spawnManager = new SpawnManager();
        BulletFactory bulletFactory = new BulletFactory();
        this.game = new Game(player, spawnManager, new MenuState(), bulletFactory);
        this.game.setPreferredSize(new Dimension(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
        this.frame.add(game);

        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        this.game.requestFocusInWindow();

        startMusic();
    }

    private void startMusic() {
        soundManager.playMusic("/assets/music.wav");
        soundManager.setMusicVolume(-10.0f);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public void start() {
        this.game.start();

    }
}
