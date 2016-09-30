package org.binu.hypersonic.tree;

import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.entity.Bomb;
import org.binu.hypersonic.entity.Bomber;
import org.binu.hypersonic.move.AbstractBomberMove;
import org.binu.hypersonic.move.BombXY;
import org.binu.hypersonic.move.BomberMove;
import org.binu.hypersonic.move.MoveXY;

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
        final ArrayList<Coordinates> validMoveCoordinates = board.getValidMoves(bomberCoordinates);

        moves.add(new MoveXY(bomberCoordinates));
        if (bomber.canPlaceBombs()) {
            moves.add(new BombXY(bomberCoordinates));
        }
        for (Coordinates validMoveCoordinate : validMoveCoordinates) {
            moves.add(new MoveXY(validMoveCoordinate));
            if (bomber.canPlaceBombs()) {
                moves.add(new BombXY(validMoveCoordinate));
            }
        }

        return moves;
    }

    public void applyMove(BomberMove bomberMove) {
        if (bomberMove.getMoveCode() == AbstractBomberMove.BOMB_CODE) {
            final Bomb bomb = new Bomb(bomber.getOwnerId(), bomber.getCoordinates(), Bomb.BOMB_HEAT, bomber.getRange());
            board.addBomb(bomb);
            board.calculateHeat();
        }

        final Coordinates coordinates = bomberMove.getCoordinates();
        bomber.setCoordinates(coordinates);
    }
}
