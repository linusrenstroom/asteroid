package main.gameobject;

import main.Vector2D;
import main.util.Point;

import java.awt.*;

public class Bullet extends GameObject{
    private final int radius = 2;

    public Bullet(Point startPos, Vector2D velocity){
        this.position = new Point(startPos.getX(), startPos.getY());
        this.velocity = velocity;
    }


    @Override
    public void update(double deltaTime) {
        position.setX(position.getX()+ deltaTime* velocity.x);
        position.setY(position.getY()+ deltaTime* velocity.y);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawLine((int)position.getX(), (int)position.getY(),
                (int)position.getX() +1, (int)position.getY() +1);
    }
}
