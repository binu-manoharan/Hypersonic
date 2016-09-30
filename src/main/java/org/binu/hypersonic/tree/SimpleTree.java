package org.binu.hypersonic.tree;

import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.entity.Bomb;
import org.binu.hypersonic.entity.Bomber;
import org.binu.hypersonic.entity.Item;

import java.util.ArrayList;
import java.util.Date;

/**
 * Simple Tree implementation
 */
public class SimpleTree {

    public static final int MAX_DEPTH = 20;
    public static final int TIME_LIMIT_IN_MS = 20;

    public TreeNode makeTree(Board board, Bomber myBomber, ArrayList<Bomb> bombs, ArrayList<Item> items) {
        board.addBombs(bombs);
        TreeNode rootNode = new TreeNode(board, myBomber);

        final long startTime = new Date().getTime();

        while (new Date().getTime() - startTime < TIME_LIMIT_IN_MS) {
            playOut(new Board(board), new Bomber(myBomber), rootNode);
        }

        return null;
    }

    private void playOut(Board board, Bomber bomber, TreeNode treeNode) {

    }
}
