package org.binu.hypersonic.tree;

import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.TestHelper;
import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.entity.Bomber;
import org.binu.hypersonic.entity.EntityHelper;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Test for {@link SimpleTree}
 */
public class SimpleTreeTest {

    private SimpleTree simpleTree;

    @Test
    public void should_create_a_single_iteration() throws Exception {
        simpleTree = new SimpleTree();
        final Bomber bomber = (Bomber) new EntityHelper().createEntity(0, 0, new Coordinates(0, 0), 1, 3);
        final Board emptyBoard = TestHelper.getEmptyBoard();
        final TreeNode treeNode = simpleTree.makeTree(emptyBoard, bomber, new ArrayList<>(), new ArrayList<>());
        emptyBoard.printBoard();
        System.out.println();
    }
}
