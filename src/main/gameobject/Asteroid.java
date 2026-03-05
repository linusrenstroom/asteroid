package main.gameobject;

import main.Vector2D;
import main.conf.GameConfig;
import main.util.Point;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Asteroid extends GameObject {
    private int radius;

    public Asteroid() {
        this.radius = GameConfig.ASTEROID_DEFAULT_RADIUS;
        this.position = new Point(GameConfig.ASTEROID_DEFAULT_START_X, GameConfig.ASTEROID_DEFAULT_START_Y);
        this.velocity = new Vector2D(GameConfig.ASTEROID_DEFAULT_VELOCITY_X, GameConfig.ASTEROID_DEFAULT_VELOCITY_Y);
    }

    public Asteroid(int radius, double posX, double posY, double velX, double velY) {
        this.radius = radius;
        this.position = new Point(posX, posY);
        this.velocity = new Vector2D(velX, velY);
    }

    @Override
    public void update(double deltaTime) {
       position.setX(position.getX()+ deltaTime* velocity.x);
       position.setY(position.getY()+ deltaTime* velocity.y);

        if (position.getX() < -GameConfig.ASTEROID_DESPAWN_MARGIN ||
                position.getX() > GameConfig.SCREEN_WIDTH + GameConfig.ASTEROID_DESPAWN_MARGIN ||
                position.getY() < -GameConfig.ASTEROID_DESPAWN_MARGIN ||
                position.getY() > GameConfig.SCREEN_HEIGHT + GameConfig.ASTEROID_DESPAWN_MARGIN) {
            this.destroy();
        }

    }
    @Override
    public void onCollision(GameObject other) {
        if (other instanceof Bullet) {
            this.destroy();
        }
    }

    @Override
    public void draw(Graphics2D g) {

        g.setColor(Color.GRAY);
        g.fillOval((int) position.getX(), (int) position.getY(), radius, radius);
    }

    @Override
    public Shape getBounds() {
        return new Ellipse2D.Double(
                position.getX() - radius,
                position.getY() - radius,
                radius * 2,
                radius * 2
        );
    }
}
