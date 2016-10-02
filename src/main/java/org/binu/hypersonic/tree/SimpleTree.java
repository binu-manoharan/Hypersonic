package org.binu.hypersonic.tree;

import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.entity.Bomb;
import org.binu.hypersonic.entity.Bomber;
import org.binu.hypersonic.entity.Item;
import org.binu.hypersonic.move.BomberMove;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Simple Tree implementation
 */
public class SimpleTree {

    public static final int MAX_DEPTH = 20;
    public static final int TIME_LIMIT_IN_MS = 50;
    Random random = new Random();
    private int count = 0;

    public void setRootNode(TreeNode rootNode) {
        this.rootNode = rootNode;
    }

    private TreeNode rootNode;

    public SimpleTree() {
        random = new Random();
        count = 0;
    }

    public TreeNode makeTree(Board board, Bomber myBomber, List<Bomb> bombs, List<Item> items) {
        rootNode = new TreeNode(board, myBomber);

        final long startTime = new Date().getTime();

        while (new Date().getTime() - startTime < TIME_LIMIT_IN_MS) {
            playOut(new Board(board), rootNode, 0);
        }

        final TreeNode lowestScoringNode = getLowestScoringNode();
        lowestScoringNode.getBoard().printBoard();
        lowestScoringNode.getBoard().printBoardHeat();

        return lowestScoringNode;
    }

    public TreeNode getLowestScoringNode() {
        System.err.println("Number of full depth simulations: " + count);
        double lowestLossRatio = 1000;
        int lowestLossIndex = 0;
        final List<TreeNode> children = rootNode.getChildren();
        for (TreeNode treeNode : children) {
            final double nodeLossRatio = ((double) treeNode.getLosses()) / treeNode.getNumberOfVisits();
            if (nodeLossRatio < lowestLossRatio) {
                lowestLossRatio = nodeLossRatio;
                lowestLossIndex = children.indexOf(treeNode);
            }
            final Coordinates coordinates = treeNode.getBomberMove().getCoordinates();
            System.err.println("Children :" + treeNode.getBomberMove().getMoveCode() + " [" +
                    coordinates.x + "," + coordinates.y + "] "
                    + treeNode.getLosses() + "/" + treeNode.getNumberOfVisits() +
                    " = " + nodeLossRatio);
        }
        return children.get(lowestLossIndex);
    }

    public void playOut(Board board, TreeNode treeNode, int depth) {

//        if (treeNode != rootNode) {
//            final BomberMove bomberMove1 = treeNode.getBomberMove();
//            Coordinates coordinates = bomberMove1.getCoordinates();
//            final Coordinates coordinates1 = treeNode.getBomber().getCoordinates();
//            System.err.print(depth + " " + board.getCell(coordinates1).getHeat() + "  " + bomberMove1.getMoveCode() + " [" +
//                    coordinates.x + "," + coordinates.y + "] ");
//            if (bomberMove1.getMoveCode() == 'B') {
//                coordinates = ((BombXY) bomberMove1).getBombCoordinates();
//                System.err.print( " bomb -  " + bomberMove1.getMoveCode() + " [" +
//                        coordinates.x + "," + coordinates.y + "] ");
//            }
//
//        } else {
//            System.err.println("");
//        }

        if (depth > MAX_DEPTH) {
            count++;
            return;
        }

        treeNode.addVisit();

        final List<BomberMove> availableMoves = treeNode.getAvailableMoves();
        final int numberOfAvailableMoves = availableMoves.size();
        final int randomIndex = random.nextInt(numberOfAvailableMoves);

        final BomberMove bomberMove = availableMoves.get(randomIndex);
        Board board1 = new Board(board);
        final Bomber bomber1 = new Bomber(treeNode.getBomber());
        TreeNode childNode = new TreeNode(board1, bomber1);
        childNode.setBomberMove(bomberMove);
        final List<TreeNode> children = treeNode.getChildren();
        if (!children.contains(childNode)) {
            treeNode.addChild(childNode);
            childNode.applyMove(bomberMove);
            board1.tickBombs();
            board1.calculateHeat();
//            System.err.print("  | N");

        } else {
            final int childIndex = children.indexOf(childNode);
            childNode = children.get(childIndex);
            board1 = childNode.getBoard();
//            System.err.print("  | E");
        }

        if (board1.getCell(bomber1.getCoordinates()).getHeat() == 0) {
            propagateLoss(treeNode);
            return;
        }
        removeExpiredBombsAndResetBombCount(board1, treeNode.getBomber());

        playOut(board1, childNode, ++depth);
    }

    private void removeExpiredBombsAndResetBombCount(Board board, Bomber bomber) {
        final List<Bomb> expiredBombs = board.removeExpiredBombs();
        for (Bomb expiredBomb : expiredBombs) {
            if (expiredBomb.getOwnerId() == bomber.getOwnerId()) {
                bomber.bombWentOffInGrid();
            }
        }
    }

    private void propagateLoss(TreeNode treeNode) {
        TreeNode tempNode = treeNode;
        while (tempNode != null) {
            tempNode.addLoss();
            tempNode = tempNode.getParent();
        }
    }
}
