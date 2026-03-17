package main.gameobject;

import main.util.Point;
import main.worldStateManagement.WorldMediator;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Explosion extends GameObject {
    private double aliveTime = 0;
    private final double duration = 0.4;
    private final List<Particle> particles = new ArrayList<>();
    private final Random random = new Random();

    private static class Particle {
        double x, y;
        double vx, vy;
        double size;
        Color color;
        double life;
        double maxLife;

        Particle(double x, double y, double vx, double vy, double size, Color color) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.size = size;
            this.color = color;

            this.life = 0;
            this.maxLife = 0.3 + Math.random() * 0.5;
        }
    }

    public Explosion(Point pos) {
        this.position = new Point(pos.getX(), pos.getY());
        for (int i = 0; i < 7; i++) {
            double angle = random.nextDouble() * Math.PI * 2;
            double speed = 50 + random.nextDouble() * 100;
            particles.add(new Particle(
                    0, 0,
                    Math.cos(angle) * speed,
                    Math.sin(angle) * speed,
                    2 + random.nextDouble() * 2,
                    random.nextBoolean() ? Color.WHITE : Color.LIGHT_GRAY
            ));
        }
    }

    @Override
    public void update(double deltaTime, WorldMediator mediator) {
        aliveTime += deltaTime;

        for (Particle p : particles) {
            p.x += p.vx * deltaTime;
            p.y += p.vy * deltaTime;
        }

        if (aliveTime >= duration) {
            destroy();
        }
    }

    @Override
    public void draw(Graphics2D g) {

        for (Particle p : particles) {

            int startX = (int) position.getX();
            int startY = (int) position.getY();

            int endX = (int) (position.getX() + p.x);
            int endY = (int) (position.getY() + p.y);

            g.setColor(Color.WHITE);

            g.drawLine(startX, startY, endX, endY);
        }
    }

    @Override
    public Shape getBounds() {
        return null;
    }
}