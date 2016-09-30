package org.binu.hypersonic.move;

import org.binu.hypersonic.Coordinates;

/**
 * A class for possible moves for the bomber
 */
public interface BomberMove {

    Coordinates getCoordinates();

    /**
     * @return string that codingame understands
     */
    String render();
}
