package org.binu.hypersonic.entity;

import org.binu.hypersonic.Coordinates;

/**
 * Player on the board
 */
public class Bomber extends Entity {
    public Bomber(int ownerId, Coordinates currentLocation, int param1, int param2) {
        super(ownerId, currentLocation, param1, param2);
    }

    public boolean canPlaceBombs() {
        return param1 > 0;
    }
}
