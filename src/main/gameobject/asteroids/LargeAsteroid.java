package main.gameobject.asteroids;

import main.Vector2D;
import java.awt.*;
import main.util.Point;

public class LargeAsteroid extends Asteroid {

    public LargeAsteroid(Point position, Vector2D direction) {
        super(
                new Polygon(
                        new int[]{0, 30, 45, 40, 15, -15, -40, -45, -30},
                        new int[]{-45, -35, -15, 20, 45, 45, 20, -15, -35},
                        9
                ),
                45,
                position,
                direction.multiply(0.5),
                0.5
        );
    }
}