package main.gameobject.asteroids;

import main.Vector2D;
import java.awt.*;

public class LargeAsteroid extends Asteroid {

    public LargeAsteroid(double x, double y, Vector2D direction) {
        super(
                new Polygon(
                        new int[]{0, 30, 45, 40, 15, -15, -40, -45, -30},
                        new int[]{-45, -35, -15, 20, 45, 45, 20, -15, -35},
                        9
                ),
                45,
                x,
                y,
                direction.multiply(0.5),
                0.5
        );
    }
}