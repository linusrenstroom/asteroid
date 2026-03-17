package main.observer;

import main.SoundManager;

public class SoundObserver implements Observer{
    private final SoundManager soundManager;

    public SoundObserver() {
        this.soundManager = new SoundManager();

        // preload sounds
        soundManager.loadSound("explosion", "/assets/explosion.wav");
    }

    @Override
    public void onEvent(Observable subject, Event event) {

        if (event == Event.ASTEROID_DESTROYED) {
            soundManager.playSound("explosion");
        }
    }
}
