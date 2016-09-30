package org.binu.hypersonic.move;

import org.binu.hypersonic.Coordinates;

/**
 *
 */
public class BombXY extends AbstractBomberMove {
    public BombXY(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String render() {
        return "BOMB " + coordinates.x + " " + coordinates.y;
    }
}
