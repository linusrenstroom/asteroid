package main.gameobject.asteroids;

import main.Vector2D;

import java.awt.*;

public class StandardAsteroid extends Asteroid {
    public StandardAsteroid(double x, double y, Vector2D direction) {
        super(
                new Polygon(new int[]{0, 15, 22, 12, -12, -22, -15}, new int[]{-22, -15, 5, 22, 22, 5, -15}, 7),
                22, x, y, direction.multiply(1), 1.2
        );
    }
}
