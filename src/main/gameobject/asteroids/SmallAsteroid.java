package main.gameobject.asteroids;

import main.Vector2D;
import main.util.Point;

import java.awt.*;

public class SmallAsteroid extends Asteroid {
    public SmallAsteroid(Point position, Vector2D direction) {
        super(
                new Polygon(new int[]{0, 12, 5, -5, -12}, new int[]{-15, 0, 15, 15, 0}, 5),
                15, position, direction.multiply(1.5), 3.0
        );
    }
}
