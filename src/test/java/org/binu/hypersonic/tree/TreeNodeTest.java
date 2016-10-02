package org.binu.hypersonic.tree;

import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.TestHelper;
import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.board.CellStatus;
import org.binu.hypersonic.board.HeatApplicatorTest;
import org.binu.hypersonic.entity.Bomb;
import org.binu.hypersonic.entity.Bomber;
import org.binu.hypersonic.entity.EntityHelper;
import org.binu.hypersonic.move.AbstractBomberMove;
import org.binu.hypersonic.move.BombXY;
import org.binu.hypersonic.move.BomberMove;
import org.binu.hypersonic.move.MoveXY;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Test for {@link TreeNode}
 */
public class TreeNodeTest {

    public static final int BOMB_RANGE = 3;
    public static final Coordinates COORDINATES_1_1 = new Coordinates(1, 1);
    public static final Coordinates COORDINATES_0_0 = new Coordinates(0, 0);
    private TreeNode treeNode;
    private Bomber bomber;
    private Board board;
    private EntityHelper entityHelper;
    private Random random;
    private HeatApplicatorTest heatApplicatorTest;

    @Before
    public void setUp() throws Exception {
        entityHelper = new EntityHelper();
        board = TestHelper.getEmptyBoard();
        initTreeNode(0, new Coordinates(1, 1));
        random = new Random();
        heatApplicatorTest = new HeatApplicatorTest();
    }

    private void initTreeNode(int numberOfBombsPlacable, Coordinates coordinates) {
        bomber = (Bomber) entityHelper.createEntity(0, 0, coordinates, numberOfBombsPlacable, BOMB_RANGE);
        treeNode = new TreeNode(board, bomber);
    }

    @Test
    public void should_get_5_valid_moves_for_bomber_at_1_1() throws Exception {
        final List availableMoves = treeNode.getAvailableMoves();
        assertThat("Bomber has moves.", availableMoves, is(notNullValue()));
        assertThat("Bomber has moves.", availableMoves.size(), is(5));
    }

    @Test
    public void should_get_2_valid_moves_for_bomber_at_1_1_with_three_obstacles() throws Exception {
        board.setCellStatus(0, 1, CellStatus.BOMB);
        board.setCellStatus(1, 0, CellStatus.BOX);
        board.setCellStatus(1, 2, CellStatus.WALL);
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
        initTreeNode(1, COORDINATES_0_0);
        final List availableMoves = treeNode.getAvailableMoves();
        assertThat("Bomber has moves.", availableMoves, is(notNullValue()));
        assertThat("Bomber has moves.", availableMoves.size(), is(6));
        assertThat("AvailableMove contains element.", availableMoves.contains(new MoveXY(new Coordinates(1, 0))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new MoveXY(new Coordinates(0, 0))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new MoveXY(new Coordinates(0, 1))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new BombXY(new Coordinates(1, 0), COORDINATES_0_0)), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new BombXY(new Coordinates(0, 0), COORDINATES_0_0)), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new BombXY(new Coordinates(0, 1), COORDINATES_0_0)), is(true));
    }

    @Test
    public void should_get_6_valid_moves_for_a_bomber_with_bombs_at_1_1() throws Exception {
        board.setCellStatus(1, 0, CellStatus.WALL);
        board.setCellStatus(0, 1, CellStatus.BOX);
        initTreeNode(1, new Coordinates(1, 1));
        final List availableMoves = treeNode.getAvailableMoves();
        assertThat("Bomber has moves.", availableMoves, is(notNullValue()));
        assertThat("Bomber has moves.", availableMoves.size(), is(6));
        assertThat("AvailableMove contains element.", availableMoves.contains(new MoveXY(new Coordinates(2, 1))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new MoveXY(new Coordinates(1, 1))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new MoveXY(new Coordinates(1, 2))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new BombXY(new Coordinates(2, 1), new Coordinates(1, 1))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new BombXY(new Coordinates(1, 1), new Coordinates(1, 1))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new BombXY(new Coordinates(1, 2), new Coordinates(1, 1))), is(true));
    }

    @Test
    public void should_get_10_valid_moves_for_a_bomber_with_bombs_at_1_1() throws Exception {
        board.addBomb((Bomb)entityHelper.createEntity(1, 0, COORDINATES_1_1, 5, 3));
        initTreeNode(1, new Coordinates(1, 1));
        final List availableMoves = treeNode.getAvailableMoves();
        assertThat("Bomber has moves.", availableMoves, is(notNullValue()));
        assertThat("Bomber has moves.", availableMoves.size(), is(9));
        assertThat("AvailableMove contains element.", availableMoves.contains(new MoveXY(new Coordinates(1, 0))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new MoveXY(new Coordinates(0, 1))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new MoveXY(new Coordinates(2, 1))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new MoveXY(new Coordinates(1, 1))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new MoveXY(new Coordinates(1, 2))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new BombXY(new Coordinates(1, 0), new Coordinates(1, 1))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new BombXY(new Coordinates(0, 1), new Coordinates(1, 1))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new BombXY(new Coordinates(2, 1), new Coordinates(1, 1))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new BombXY(new Coordinates(1, 2), new Coordinates(1, 1))), is(true));
    }

    @Test
    public void should_get_10_valid_moves_for_a_bomber_with_bombs_at_1_1_andblah() throws Exception {
        initTreeNode(1, COORDINATES_1_1);
        final List availableMoves = treeNode.getAvailableMoves();
        assertThat("Bomber has moves.", availableMoves, is(notNullValue()));
        assertThat("Bomber has moves.", availableMoves.size(), is(10));
        assertThat("AvailableMove contains element.", availableMoves.contains(new MoveXY(new Coordinates(1, 0))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new MoveXY(new Coordinates(0, 1))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new MoveXY(new Coordinates(2, 1))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new MoveXY(new Coordinates(1, 1))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new MoveXY(new Coordinates(1, 2))), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new BombXY(new Coordinates(1, 0), COORDINATES_1_1)), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new BombXY(new Coordinates(0, 1), COORDINATES_1_1)), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new BombXY(new Coordinates(2, 1), COORDINATES_1_1)), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new BombXY(new Coordinates(1, 1), COORDINATES_1_1)), is(true));
        assertThat("AvailableMove contains element.", availableMoves.contains(new BombXY(new Coordinates(1, 2), COORDINATES_1_1)), is(true));
    }

    @Test
    public void should_apply_move_to_xy_on_bomber() throws Exception {
        final List<BomberMove> availableMoves = treeNode.getAvailableMoves();
        final int moveSize = availableMoves.size();
        assertThat("There are available moves", moveSize > 0, is(true));

        final BomberMove bomberMove = availableMoves.get(random.nextInt(moveSize));
        treeNode.applyMove(bomberMove);

        final Coordinates bomberMoveCoordinates = bomberMove.getCoordinates();
        assertThat("Bomber should be at the location", bomber.getCoordinates(), is(bomberMoveCoordinates));
    }

    @Test
    public void should_apply_bomb_current_coordinate_and_move_bomber_to_location() throws Exception {
        initTreeNode(1, new Coordinates(1, 1));
        final List<BomberMove> availableMoves = treeNode.getAvailableMoves();
        final int moveSize = availableMoves.size();
        assertThat("There are available moves", moveSize > 0, is(true));

        BomberMove bomberMove = null;
        for (BomberMove availableMove : availableMoves) {
            if (availableMove.getMoveCode() == AbstractBomberMove.BOMB_CODE) {
                bomberMove = availableMove;
                break;
            }
        }
        final Coordinates bomberOldCoordinates = bomber.getCoordinates();
        treeNode.applyMove(bomberMove);
        board.calculateHeat();
        final CellStatus cellStatus = board.getCellStatus(bomberOldCoordinates);
        assertThat("Bomb has been placed on bombers previous coordinate.", cellStatus, is(CellStatus.BOMB));

        final Coordinates bomberMoveCoordinates = bomberMove.getCoordinates();
        assertThat("Bomber should be at the location", bomber.getCoordinates(), is(bomberMoveCoordinates));

        heatApplicatorTest.setBoard(board);
        heatApplicatorTest.assertHeatInRange(bomberOldCoordinates, Bomb.BOMB_HEAT, BOMB_RANGE);
    }
}
