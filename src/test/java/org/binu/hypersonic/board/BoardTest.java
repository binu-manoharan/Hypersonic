package org.binu.hypersonic.board;

import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.entity.Bomb;
import org.binu.hypersonic.entity.EntityHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.binu.hypersonic.TestHelper.getEmptyBoard;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test for {@link Board}
 */
public class BoardTest {

    public static final Coordinates COORDINATES_0_0 = new Coordinates(0, 0);
    public static final int HEAT_5 = 5;
    public static final int HEAT_0 = 0;
    public static final int HEAT_1 = 1;
    private Board board;
    private Cell[] rowCells;
    private EntityHelper entityHelper;

    @Before
    public void setUp() throws Exception {
        board = getEmptyBoard();
        entityHelper = new EntityHelper();
        rowCells = new Cell[Board.BOARD_WIDTH];
    }

    @Test
    public void should_create_a_row_of_board_cells() throws Exception {
        for (int x = 0; x < Board.BOARD_WIDTH; x++) {
            rowCells[x] = new Cell(CellStatus.BOX);
        }

        board.setRow(0, rowCells);

        final Cell[] row = board.getRow(0);
        for (int x = 0; x < Board.BOARD_WIDTH; x++) {
            assertThat("row is filled with box", row[x].getCellStatus(), is(CellStatus.BOX));
        }
    }

    @Test
    public void should_get_2_moves_for_coordinates_0_0() throws Exception {
        final List<Coordinates> validMoves = board.getValidMoves(new Coordinates(0, 0));
        assertThat("There should be 2 valid moves.", validMoves.size(), is(2));
    }

    @Test
    public void should_get_1_move_for_coordinates_0_0_with_an_obstacle() throws Exception {
        board.setCellStatus(1, 0, CellStatus.WALL);
        final List<Coordinates> validMoves = board.getValidMoves(new Coordinates(0,0));
        assertThat("There should be a valid move.", validMoves.size(), is(1));
    }

    @Test
    public void should_get_no_move_for_coordinates_0_0_with_obstacles() throws Exception {
        board.setCellStatus(1, 0, CellStatus.BOMB);
        board.setCellStatus(0, 1, CellStatus.BOX);
        final List<Coordinates> validMoves = board.getValidMoves(new Coordinates(0,0));
        assertThat("There should be no valid move.", validMoves.size(), is(0));
    }

    @Test
    public void should_get_4_moves_for_coordinates_1_1() throws Exception {
        final List<Coordinates> validMoves = board.getValidMoves(new Coordinates(1,1));
        assertThat("There should be 4 valid moves.", validMoves.size(), is(4));
    }

    @Test
    public void should_get_1_move_for_coordinates_1_1_with_obstacles() throws Exception {
        board.setCellStatus(1, 0, CellStatus.WALL);
        board.setCellStatus(0, 1, CellStatus.BOMB);
        board.setCellStatus(2, 1, CellStatus.BOX);
        final List<Coordinates> validMoves = board.getValidMoves(new Coordinates(1,1));
        assertThat("There should be a valid move.", validMoves.size(), is(1));
        assertThat("Valid move contains 1,2", validMoves.get(0), is(new Coordinates(1, 2)));
    }

    @Test
    public void should_be_able_to_move_to_empty_cell() throws Exception {
        final boolean isMovable = board.coordinateIsMovable(new Coordinates(0, 0));
        assertThat("0, 0 is movable.", isMovable, is(true));
    }

    @Test
    public void should_not_be_able_to_move_to_cell_with_box() throws Exception {
        board.setCellStatus(0, 0, CellStatus.BOX);
        final boolean isMovable = board.coordinateIsMovable(new Coordinates(0, 0));
        assertThat("0, 0 is not movable.", isMovable, is(false));
    }

    @Test
    public void should_not_be_able_to_move_to_cell_with_wall() throws Exception {
        board.setCellStatus(0, 0, CellStatus.WALL);
        final boolean isMovable = board.coordinateIsMovable(new Coordinates(0, 0));
        assertThat("0, 0 is not movable.", isMovable, is(false));
    }

    @Test
    public void should_not_be_able_to_move_to_cell_with_bomb() throws Exception {
        board.setCellStatus(0, 0, CellStatus.BOMB);
        final boolean isMovable = board.coordinateIsMovable(COORDINATES_0_0);
        assertThat("0, 0 is not movable.", isMovable, is(false));
    }

    @Test
    public void should_reduce_heat_by_1_for_bomb() throws Exception {
        board.addBomb((Bomb)entityHelper.createEntity(1, 0, COORDINATES_0_0, HEAT_5, 3));
        board.tickBombs();
        board.calculateHeat();
        final Cell cell = board.getCell(0, 0);
        assertThat("0, 0 heat.", cell.getHeat(), is(HEAT_5 - 1));
    }

    @Test
    public void should_set_heat_to_0_and_not_remove_bomb() throws Exception {
        board.addBomb((Bomb)entityHelper.createEntity(1, 0, COORDINATES_0_0, HEAT_1, 3));
        board.tickBombs();
        board.calculateHeat();
        final Cell cell = board.getCell(0, 0);
        assertThat("0, 0 heat.", cell.getHeat(), is(HEAT_1 - 1));
        assertThat("Bomb has been removed.", board.getHeatApplicator().getBombs().size(), is(1));
    }

    @Test
    public void should_reset_heat_and_remove_bomb() throws Exception {
        board.addBomb((Bomb)entityHelper.createEntity(1, 0, COORDINATES_0_0, HEAT_0, 3));
        //this is needed to initialise the board on heat applicator
        board.calculateHeat();
        board.tickBombs();
        board.calculateHeat();
        board.removeExpiredBombs();
        final Cell cell = board.getCell(0, 0);
        assertThat("0, 0 heat.", cell.getHeat(), is(HEAT_0 - 1));
        assertThat("Bomb has been removed.", board.getHeatApplicator().getBombs().size(), is(0));
    }
}
