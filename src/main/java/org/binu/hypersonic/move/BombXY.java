package org.binu.hypersonic.move;

import org.binu.hypersonic.Coordinates;

/**
 * Bomb current location and move to XY
 */
public class BombXY extends AbstractBomberMove {

    public BombXY(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public char getMoveCode() {
        return BOMB_CODE;
    }

    @Override
    public String render() {
        return "BOMB " + coordinates.x + " " + coordinates.y;
    }
}
