package org.binu.hypersonic.tree;

import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.TestHelper;
import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.board.CellStatus;
import org.binu.hypersonic.entity.Bomber;
import org.binu.hypersonic.entity.EntityHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Test for {@link TreeNode}
 */
public class TreeNodeTest {

    private TreeNode treeNode;
    private Bomber bomber;
    private Board emptyBoard;
    private EntityHelper entityHelper;

    @Before
    public void setUp() throws Exception {
        entityHelper = new EntityHelper();
        emptyBoard = TestHelper.getEmptyBoard();
        initTreeNode(0, new Coordinates(1, 1));
    }

    private void initTreeNode(int numberOfBombsPlacable, Coordinates coordinates) {
        bomber = (Bomber) entityHelper.createEntity(0, 0, coordinates, numberOfBombsPlacable, 3);
        treeNode = new TreeNode(emptyBoard, bomber);
    }

    @Test
    public void should_get_5_valid_moves_for_bomber_at_1_1() throws Exception {
        final List availableMoves = treeNode.getAvailableMoves();
        assertThat("Bomber has moves.", availableMoves, is(notNullValue()));
        assertThat("Bomber has moves.", availableMoves.size(), is(5));
    }

    @Test
    public void should_get_2_valid_moves_for_bomber_at_1_1_with_three_obstacles() throws Exception {
        emptyBoard.setCellStatus(0, 1, CellStatus.BOMB);
        emptyBoard.setCellStatus(1, 0, CellStatus.BOX);
        emptyBoard.setCellStatus(1, 2, CellStatus.WALL);
        final List availableMoves = treeNode.getAvailableMoves();
        assertThat("Bomber has moves.", availableMoves, is(notNullValue()));
        assertThat("Bomber has moves.", availableMoves.size(), is(2));
    }

    @Test
    public void should_get_3_valid_moves_for_bomber_at_0_0() throws Exception {
        initTreeNode(0, new Coordinates(0, 0));
        final List availableMoves = treeNode.getAvailableMoves();
        assertThat("Bomber has moves.", availableMoves, is(notNullValue()));
        assertThat("Bomber has moves.", availableMoves.size(), is(3));
    }

    @Test
    public void should_get_6_valid_moves_for_a_bomber_with_bombs_at_0_0() throws Exception {
        initTreeNode(1, new Coordinates(0, 0));
        final List availableMoves = treeNode.getAvailableMoves();
        assertThat("Bomber has moves.", availableMoves, is(notNullValue()));
        assertThat("Bomber has moves.", availableMoves.size(), is(6));
    }

    @Test
    public void should_get_6_valid_moves_for_a_bomber_with_bombs_at_1_1() throws Exception {
        emptyBoard.setCellStatus(1, 0, CellStatus.WALL);
        emptyBoard.setCellStatus(0, 1, CellStatus.BOX);
        initTreeNode(1, new Coordinates(1, 1));
        final List availableMoves = treeNode.getAvailableMoves();
        assertThat("Bomber has moves.", availableMoves, is(notNullValue()));
        assertThat("Bomber has moves.", availableMoves.size(), is(6));
    }


}
