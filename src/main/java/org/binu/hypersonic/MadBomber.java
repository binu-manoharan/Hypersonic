package org.binu.hypersonic;

import org.binu.hypersonic.entity.Bomb;
import org.binu.hypersonic.entity.Bomber;
import org.binu.hypersonic.move.BomberMove;

import java.util.List;

/**
 * A person with bombs, who has lost his sanity and sense.
 * This bomber has nothing to lose or fear.
 * Be careful when you ask him for his next move.
 */
public class MadBomber {

    private final int myId;
    private final char[][] board;
    private final List<Bomber> bombers;
    private final List<Bomb> bombs;

    public MadBomber(int myId, char[][] board, List<Bomber> bombers, List<Bomb> bombs) {
        this.myId = myId;
        this.board = board;
        this.bombers = bombers;
        this.bombs = bombs;
    }

    public BomberMove calculateNextMove() {
        return null;
    }
}
