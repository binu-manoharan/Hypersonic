package org.binu.hypersonic.entity;

import org.binu.hypersonic.Coordinates;

/**
 * Represents bombs
 */
public class Bomb extends Entity {
    public static final int BOMB_HEAT = 8;

    public Bomb(int ownerId, Coordinates currentLocation, int param1, int param2) {
        super(ownerId, currentLocation, param1, param2);
    }

    public int getHeat() {
        return param1;
    }

    public void setHeat(int heat) {
        this.param1 = heat;
    }
}
