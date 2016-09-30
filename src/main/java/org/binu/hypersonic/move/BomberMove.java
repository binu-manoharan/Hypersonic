package org.binu.hypersonic.move;

import org.binu.hypersonic.Coordinates;

/**
 * A class for possible moves for the bomber
 */
public interface BomberMove {

    Coordinates getCoordinates();

    /**
     * B for bomb and M for move
     *
     * @return a char code for the move
     */
    char getMoveCode();

    /**
     * @return string that codingame understands
     */
    String render();
}
