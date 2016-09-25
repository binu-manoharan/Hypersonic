package org.binu.hypersonic.entity;

import org.binu.hypersonic.Coordinates;

/**
 * A bomber or a bomb, you wouldn't think they'd have much to share inheritance structure.
 * I guess bomber works closely with a bomb? :o
 */
public abstract class Entity {
    private int ownerId;
    private Coordinates currentLocation;
    protected int param1;
    protected int param2;

    public Entity(int ownerId, Coordinates currentLocation, int param1, int param2) {
        this.ownerId = ownerId;
        this.currentLocation = currentLocation;
        this.param1 = param1;
        this.param2 = param2;
    }

    public int getEntityType() {
        if (this instanceof Bomber) {
            return 0;
        } else if (this instanceof Bomb) {
            return 1;
        } else {
            assert false : "Another ghost lurking around in the code.";
            return -1;
        }
    }

    public Coordinates getCurrentLocation() {
        return currentLocation;
    }
}
