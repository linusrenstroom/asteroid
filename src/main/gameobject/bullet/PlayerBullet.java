package main.gameobject.bullet;

import main.Vector2D;
import main.util.Point;

import java.awt.*;

public class PlayerBullet extends Bullet {

    public PlayerBullet(Point startPos, Vector2D direction) {
        super(startPos, direction);
        this.setColor(Color.RED);

    }

}
