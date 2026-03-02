package main.gameobject;

import main.Vector2D;

import java.awt.*;
import main.util.Point;

public abstract class GameObject {

    protected Point position;
    protected Vector2D velocity;

    public abstract void update(double deltaTime);

    public abstract void draw(Graphics2D g);

}
