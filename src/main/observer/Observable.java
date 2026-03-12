package main.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(Event event) {
        observers.forEach(o -> o.onEvent(this, event));
    }

    protected void notifyObservers() {
        observers.forEach(o -> o.onEvent(this));
    }
}
