package main.ui;

import main.observer.UiObserver;
import main.worldStateManagement.World;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    private final World world;
    private final List<UiObserver> uiObservers = new ArrayList<>();

    public GamePanel(World world) {
        this.world = world;
        setFocusable(true);
        setBackground(Color.BLACK);
    }

    public void addUiObserver(UiObserver observer) {
        uiObservers.add(observer);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        world.draw(g2);
        world.getGameState().draw(g2);
        for (UiObserver obs : uiObservers) {
            obs.draw(g2);
        }
    }
}