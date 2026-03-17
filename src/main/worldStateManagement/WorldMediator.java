package main.worldStateManagement;

import main.gameobject.Player;

/**
 * Minimal mediator interface used by game objects to query the world
 * without directly referencing other objects.
 */
public interface WorldMediator {
    Player getPlayer();
}
