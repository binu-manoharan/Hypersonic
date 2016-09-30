package org.binu.hypersonic.move;

import org.binu.hypersonic.Coordinates;

/**
 * Move to coordinates command for bomber
 */
public class MoveXY extends AbstractBomberMove {

    public MoveXY(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String render() {
        return "MOVE " + coordinates.x + " " + coordinates.y;
    }
}
