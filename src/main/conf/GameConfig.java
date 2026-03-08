package main.conf;

import java.awt.event.KeyEvent;

public class GameConfig {
    public static final String WINDOW_TITLE = "Asteroid";
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;

    public static final int GAME_LOOP_DELAY_MS = 16;
    public static final double NANOS_PER_SECOND = 1_000_000_000.0;

    public static final double PLAYER_START_X_RATIO = 0.5;
    public static final double PLAYER_START_Y_RATIO = 0.5;
    public static final double PLAYER_SHOOT_COOLDOWN = 0.2;
    public static final double PLAYER_ROTATION_SPEED = 3.5;
    public static final double PLAYER_ACCELERATION = 400.0;
    public static final double PLAYER_DRAG_PER_SECOND = 0.15;
    public static final double PLAYER_BULLET_SPEED = 600.0;
    public static final double PLAYER_HEADING_OFFSET_RADIANS = Math.PI / 2.0;
    public static final int[] PLAYER_SHIP_X_POINTS = {0, -10, 10};
    public static final int[] PLAYER_SHIP_Y_POINTS = {-15, 10, 10};
    public static final int PLAYER_SHIP_POINT_COUNT = 3;

    public static final int BULLET_RADIUS = 4;
    public static final int ASTEROID_DEFAULT_RADIUS = 20;
    public static final double ASTEROID_DEFAULT_START_X = 0.0;
    public static final double ASTEROID_DEFAULT_START_Y = 0.0;
    public static final double ASTEROID_DEFAULT_VELOCITY_X = 10.0;
    public static final double ASTEROID_DEFAULT_VELOCITY_Y = 0.0;
    public static final double ASTEROID_DESPAWN_MARGIN = 100.0;

    public static final double SPAWN_OFFSCREEN_MARGIN = 50.0;
    public static final double SPAWN_TARGET_X_OFFSET_FACTOR = 0.5;
    public static final double SPAWN_TARGET_X_RANGE_FACTOR = 0.25;
    public static final double SPAWN_TARGET_Y_BASE_FACTOR = 0.25;
    public static final double SPAWN_TARGET_Y_RANGE_FACTOR = 0.5;
    public static final double SPAWN_ASTEROID_BASE_SPEED = 100.0;
    public static final double SPAWN_ASTEROID_SPEED_INCREASE_PER_SECOND = 2.0;
    public static final double BASE_SPAWN_RATE_SECONDS = 1;

    public static final int PAUSED_OVERLAY_ALPHA = 128;
    public static final String PAUSED_TEXT = "PAUSAT";

}