package org.binu.hypersonic.tree;

import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.board.CellStatus;
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
    private int numberOfVisits;
    private int wins;
    private int losses;
    private int boxes;
    private BomberMove bomberMove;
    private Bomber bomber;


    private List<TreeNode> children;
    private TreeNode parent;

    public TreeNode(Board board, Bomber bomber) {
        this.board = board;
        children = new ArrayList<>();
        this.bomber = bomber;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public List<BomberMove> getAvailableMoves() {
        final ArrayList<BomberMove> moves = new ArrayList<>();
        final Coordinates bomberCoordinates = bomber.getCoordinates();
        final ArrayList<Coordinates> validMoveCoordinates = board.getValidMoves(bomberCoordinates);

        moves.add(new MoveXY(bomberCoordinates));
        if (bomber.canPlaceBombs() && board.getCellStatus(bomberCoordinates) != CellStatus.BOMB) {
            moves.add(new BombXY(bomberCoordinates, bomberCoordinates));
        }
        for (Coordinates validMoveCoordinate : validMoveCoordinates) {
            moves.add(new MoveXY(validMoveCoordinate));
            //TODO add test here
            if (bomber.canPlaceBombs() && board.getCellStatus(bomberCoordinates) != CellStatus.BOMB) {
                moves.add(new BombXY(validMoveCoordinate, bomberCoordinates));
            }
        }

        return moves;
    }

    public void applyMove(BomberMove bomberMove) {
        if (bomberMove.getMoveCode() == AbstractBomberMove.BOMB_CODE) {
            final Bomb bomb = new Bomb(bomber.getOwnerId(), bomber.getCoordinates(), Bomb.BOMB_HEAT, bomber.getRange());
            final boolean placedBomb = board.addBomb(bomb);
            assert placedBomb : "Bomb already exists?!?!?" + bomber.getCoordinates();
            bomber.placedABomb();
        }

        final Coordinates coordinates = bomberMove.getCoordinates();
        this.bomber.setCoordinates(coordinates);

    }

    public void addChild(TreeNode childNode) {
        children.add(childNode);
        childNode.setParent(this);
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TreeNode)) return false;

        TreeNode treeNode = (TreeNode) o;

        assert bomberMove != null;
        return bomberMove.equals(treeNode.bomberMove);
    }

    @Override
    public int hashCode() {
        return bomberMove.toString() != null ? bomberMove.toString().hashCode() : 0;
    }

    public void setBomberMove(BomberMove bomberMove) {
        this.bomberMove = bomberMove;
    }

    public void addVisit() {
        numberOfVisits++;
    }

    public void addLoss() {
        losses++;
    }

    public TreeNode getParent() {
        return parent;
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public int getLosses() {
        return losses;
    }

    public BomberMove getBomberMove() {
        return bomberMove;
    }

    public Board getBoard() {
        return board;
    }

    public Bomber getBomber() {
        return bomber;
    }

    public void addWin() {
        wins++;
    }

    public int getWins() {
        return wins;
    }
}
