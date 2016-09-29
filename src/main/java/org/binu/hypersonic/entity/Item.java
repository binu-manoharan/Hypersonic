package org.binu.hypersonic.entity;

import org.binu.hypersonic.Coordinates;

/**
 * An item that is dropped by destroying a crate/box
 */
public class Item extends Entity {
    public Item(Coordinates currentLocation, int param1) {
        super(0, currentLocation, param1, 0);
    }
}
