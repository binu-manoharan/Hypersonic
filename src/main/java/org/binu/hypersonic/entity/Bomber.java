package org.binu.hypersonic.entity;

import org.binu.hypersonic.Coordinates;

/**
 * Player on the board
 */
public class Bomber extends Entity {
    public Bomber(int ownerId, Coordinates currentLocation, int param1, int param2) {
        super(ownerId, currentLocation, param1, param2);
    }

    public Bomber(Bomber bomber) {
        super(bomber.getOwnerId(), bomber.getCoordinates(), bomber.getNumberOfBombs(), bomber.getRange());
    }

    public int getNumberOfBombs() {
        return param1;
    }
    public boolean canPlaceBombs() {
        return param1 > 0;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void placedABomb() {
        param1--;
    }

    public void bombWentOffInGrid() {
        param1++;
    }
}
