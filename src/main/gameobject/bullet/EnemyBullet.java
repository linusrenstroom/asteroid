package main.gameobject.bullet;

import main.Vector2D;
import main.gameobject.GameObject;
import main.gameobject.Player;
import main.observer.Event;
import main.util.Point;

import java.awt.*;

public class EnemyBullet extends Bullet {

    public EnemyBullet(Point startPos, Vector2D direction) {
        super(startPos, direction);
        setColor(Color.RED);
    }
    @Override
    public void onCollision(GameObject other) {
        if(other instanceof Player){
            this.destroy();
        }
    }
}
