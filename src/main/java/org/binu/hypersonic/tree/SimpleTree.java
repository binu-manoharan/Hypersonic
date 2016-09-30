package org.binu.hypersonic.tree;

import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.board.Cell;
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

    public SimpleTree() {
        random = new Random();
        count = 0;
    }

    public TreeNode makeTree(Board board, Bomber myBomber, List<Bomb> bombs, List<Item> items) {
        board.addBombs(bombs);
        board.calculateHeat();
        TreeNode rootNode = new TreeNode(board, myBomber);

        final long startTime = new Date().getTime();

        while (new Date().getTime() - startTime < TIME_LIMIT_IN_MS) {
            playOut(new Board(board), new Bomber(myBomber), rootNode, 0);
        }
        System.err.println("Number of simulations: " + count);
        double lowestLossRatio = 100;
        int lowestLossIndex = 0;
        final List<TreeNode> children = rootNode.getChildren();
        for (TreeNode treeNode : children) {
            final double nodeLossRatio = ((double) treeNode.getLosses()) / treeNode.getNumberOfVisits();
            if (nodeLossRatio < lowestLossRatio) {
                lowestLossRatio = nodeLossRatio;
                lowestLossIndex = children.indexOf(treeNode);
            }
            System.err.println("Children :" + treeNode.getLosses() + "/" + treeNode.getNumberOfVisits());
        }
        return children.get(lowestLossIndex);
    }

    private void playOut(Board board, Bomber bomber, TreeNode treeNode, int depth) {
        if (depth > MAX_DEPTH) {
            count++;
//            board.printBoard();
//            System.err.println("*************");
            return;
        }

        final Coordinates bomberCoordinates = bomber.getCoordinates();
        board.tickBombs();
        board.calculateHeat();
        final Cell bomberCell = board.getCell(bomberCoordinates);
        if (bomberCell.getHeat() == 0) {
            propagateLoss(treeNode);
            return;
        }

        final List<BomberMove> availableMoves = treeNode.getAvailableMoves();
        final int numberOfAvailableMoves = availableMoves.size();
        final int randomIndex = random.nextInt(numberOfAvailableMoves);

        final BomberMove bomberMove = availableMoves.get(randomIndex);
        TreeNode childNode = new TreeNode(board, bomber);
        childNode.setBomberMove(bomberMove);
        final List<TreeNode> children = treeNode.getChildren();
        if (!children.contains(childNode)) {
            childNode.applyMove(bomberMove);
            treeNode.addChild(childNode);
        } else {
            final int childIndex = children.indexOf(childNode);
            childNode = children.get(childIndex);
        }

        childNode.addVisit();
        playOut(board, bomber, childNode, ++depth);
    }

    private void propagateLoss(TreeNode treeNode) {
        while (treeNode.getParent() != null) {
            treeNode.addLoss();
            treeNode = treeNode.getParent();
        }
    }
}
