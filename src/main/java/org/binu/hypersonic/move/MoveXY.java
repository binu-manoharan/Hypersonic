package org.binu.hypersonic.move;

import org.binu.hypersonic.Coordinates;

/**
 * Move to coordinates command for bomber
 */
public class MoveXY implements BomberMove {
    private final Coordinates coordinates;

    public MoveXY(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public String render() {
        return "MOVE " + coordinates.x + " " + coordinates.y;
    }
}
