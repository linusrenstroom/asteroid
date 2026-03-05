package main.gameobject;

import main.Vector2D;
import main.conf.GameConfig;
import main.gameobject.GameObject;
import main.util.Point;

import java.awt.*;

public class Bullet extends GameObject {

    public Bullet(double posX, double posY, Vector2D velocity) {
        this.position = new Point(posX, posY);
        this.velocity = velocity;
    }

    @Override
    public void update(double deltaTime) {
        position.setX(position.getX() + velocity.x * deltaTime);
        position.setY(position.getY() + velocity.y * deltaTime);

        if (position.getX() < 0 || position.getX() > GameConfig.SCREEN_WIDTH
                || position.getY() < 0 || position.getY() > GameConfig.SCREEN_HEIGHT) {
            destroy();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.fillOval((int) position.getX() - 2, (int) position.getY() - 2, 4, 4);
    }
}