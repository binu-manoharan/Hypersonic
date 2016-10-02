package org.binu.hypersonic.tree;

import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.TestHelper;
import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.board.CellStatus;
import org.binu.hypersonic.entity.Bomb;
import org.binu.hypersonic.entity.Bomber;
import org.binu.hypersonic.entity.EntityHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test for {@link SimpleTree}
 */
public class SimpleTreeTest {

    private SimpleTree simpleTree;
    private EntityHelper entityHelper;
    private Bomber bomber;
    private Board board;
    private Bomb bomb;

    @Before
    public void setUp() throws Exception {
        simpleTree = new SimpleTree();
        entityHelper = new EntityHelper();
        bomber = (Bomber) entityHelper.createEntity(0, 0, new Coordinates(0, 0), 1, 3);
        board = TestHelper.getEmptyBoard();
        bomb = (Bomb) entityHelper.createEntity(1, 0, new Coordinates(1, 0), 8, 3);
        board.addBomb(bomb);
    }

    @Test
    public void should_create_a_single_iteration() throws Exception {
        board.setCellStatus(1, 1, CellStatus.WALL);
        board.setCellStatus(0, 2, CellStatus.WALL);
        board.calculateHeat();
        final TreeNode rootNode = new TreeNode(board, bomber);
        simpleTree.setRootNode(rootNode);
        long startTime = new Date().getTime();
        while(new Date().getTime() - startTime < 100) {
            simpleTree.playOut(new Board(board), rootNode, 0);
        }
        simpleTree.playOut(new Board(board), rootNode, 0);
        simpleTree.playOut(new Board(board), rootNode, 0);
        simpleTree.playOut(new Board(board), rootNode, 0);
        simpleTree.playOut(new Board(board), rootNode, 0);
        simpleTree.playOut(new Board(board), rootNode, 0);
        simpleTree.playOut(new Board(board), rootNode, 0);

        assertThat("rootNode is not null", rootNode, is(notNullValue()));
        assertThat("rootNode has 4 children", rootNode.getChildren().size(), is(4));
        simpleTree.getLowestScoringNode();
    }
}
