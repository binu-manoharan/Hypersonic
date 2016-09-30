package org.binu.hypersonic.move;

import org.binu.hypersonic.Coordinates;

/**
 *
 */
public class BombXY extends AbstractBomberMove {
    private final Coordinates coordinates;

    public BombXY(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public String render() {
        return "BOMB " + coordinates.x + " " + coordinates.y;
    }
}
