package org.binu.hypersonic.tree;

import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.entity.Bomber;
import org.binu.hypersonic.move.BomberMove;

import java.util.ArrayList;
import java.util.List;

/**
 * A node representing a game state of the game.
 */
public class TreeNode {
    private final Board board;
    private final Bomber bomber;
    private int numberOfVisits;
    private int wins;
    private int losses;
    private int boxes;

    private List<TreeNode> children;

    public TreeNode(Board board, Bomber bomber) {
        this.board = board;
        this.bomber = bomber;
        children = new ArrayList<>();
    }

    public List<BomberMove> getAvailableMoves() {
        final ArrayList<BomberMove> moves = new ArrayList<>();
        final Coordinates bomberCoordinates = bomber.getCoordinates();
        board.getValidMoves(bomberCoordinates);
        return moves;
    }
}
