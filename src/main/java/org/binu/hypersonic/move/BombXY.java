package org.binu.hypersonic.move;

import org.binu.hypersonic.Coordinates;

/**
 *
 */
public class BombXY implements BomberMove {
    private final Coordinates coordinates;

    public BombXY(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String render() {
        return "BOMB " + coordinates.x + " " + coordinates.y;
    }
}
