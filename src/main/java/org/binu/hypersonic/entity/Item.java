package org.binu.hypersonic.entity;

import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.board.CellItem;

/**
 * An item that is dropped by destroying a crate/box
 */
public class Item extends Entity {
    public Item(Coordinates currentLocation, int param1) {
        super(0, currentLocation, param1, 0);
        System.err.println("Item: [" + currentLocation.x + " " + currentLocation.y + "] " + param1 );
    }

    public CellItem getItemType() {
        if (param1 == 1)
            return CellItem.BONUS_RANGE;
        if (param2 == 2)
            return CellItem.BONUS_BOMBS;
        assert false : "They said there would be items!";
        return null;
    }
}
