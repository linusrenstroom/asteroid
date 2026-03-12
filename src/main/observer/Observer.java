package main.observer;

public interface Observer {
    default void onEvent(Observable subject) {};
    default void onEvent(Observable subject, Event event) {};
}
