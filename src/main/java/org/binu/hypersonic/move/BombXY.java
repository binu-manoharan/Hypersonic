package org.binu.hypersonic.move;

import org.binu.hypersonic.Coordinates;

/**
 * Bomb current location and move to XY
 */
public class BombXY extends AbstractBomberMove {

    private final Coordinates bombCoordinates;

    public BombXY(Coordinates moveCordinates, Coordinates bombCoordinates) {
        super(moveCordinates);
        this.bombCoordinates = bombCoordinates;
    }

    @Override
    public char getMoveCode() {
        return BOMB_CODE;
    }

    public Coordinates getBombCoordinates() {
        return bombCoordinates;
    }

    @Override
    public String render() {
        return "BOMB " + coordinates.x + " " + coordinates.y;
    }

    @Override
    public String toString() {
        return render() + " BC=" + bombCoordinates.x + " " + bombCoordinates.y;
    }

    @Override
    public boolean equals(Object o) {
        return this.toString().equals(o.toString());
    }
}
