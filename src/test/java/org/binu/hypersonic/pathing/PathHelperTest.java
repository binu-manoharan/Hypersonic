package org.binu.hypersonic.pathing;

import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.TestHelper;
import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.board.CellStatus;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Test for {@link PathHelper}
 */
public class PathHelperTest {

    private Board emptyBoard;
    private PathHelper pathHelper;
    private Coordinates coordinates1;
    private Coordinates coordinates2;

    @Before
    public void setUp() throws Exception {
        emptyBoard = TestHelper.getEmptyBoard();
        pathHelper = new PathHelper();
    }

    @Test
    public void should_find_the_path_to_be_1_for_adjacent_cells() throws Exception {
        coordinates1 = new Coordinates(0, 0);
        coordinates2 = new Coordinates(1, 0);
        final int shortestDistance = pathHelper.findShortestDistance(emptyBoard, coordinates1, coordinates2);
        assertThat("Shortest distance between adjacent cells is 1.", shortestDistance, is(1));
    }

    @Test
    public void should_find_the_path_to_be_2() throws Exception {
        coordinates1 = new Coordinates(0, 0);
        coordinates2 = new Coordinates(2, 0);
        final int shortestDistance = pathHelper.findShortestDistance(emptyBoard, coordinates1, coordinates2);
        assertThat("Shortest distance to be 2.", shortestDistance, is(2));
    }

    @Test
    public void should_find_the_path_to_be_4_as_there_is_an_obstacle() throws Exception {
        coordinates1 = new Coordinates(0, 0);
        coordinates2 = new Coordinates(2, 0);
        emptyBoard.setCellStatus(1, 0, CellStatus.WALL);
        emptyBoard.printBoard();
        final int shortestDistance = pathHelper.findShortestDistance(emptyBoard, coordinates1, coordinates2);
        assertThat("Shortest distance to be 4 due to obstacle.", shortestDistance, is(4));
    }

    @Test
    public void should_find_the_path_to_be_22_on_an_empty_board() throws Exception {
        coordinates1 = new Coordinates(0, 0);
        coordinates2 = new Coordinates(12, 10);
        emptyBoard.printBoard();
        final int shortestDistance = pathHelper.findShortestDistance(emptyBoard, coordinates1, coordinates2);
        assertThat("Shortest distance to be 22.", shortestDistance, is(22));
    }

    @Test
    public void should_find_the_path_to_be_22_with_obstacles() throws Exception {
        coordinates1 = new Coordinates(0, 0);
        coordinates2 = new Coordinates(2, 0);
        emptyBoard.setCellStatus(1, 0, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 1, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 2, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 3, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 4, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 5, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 6, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 7, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 8, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 9, CellStatus.WALL);

        emptyBoard.printBoard();
        final int shortestDistance = pathHelper.findShortestDistance(emptyBoard, coordinates1, coordinates2);
        assertThat("Shortest distance to be 22 due to obstacle.", shortestDistance, is(22));
    }

    @Test
    public void should_find_the_path_to_be_unreachable_due_to_obstacles() throws Exception {
        coordinates1 = new Coordinates(0, 0);
        coordinates2 = new Coordinates(2, 0);
        emptyBoard.setCellStatus(1, 0, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 1, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 2, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 3, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 4, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 5, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 6, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 7, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 8, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 9, CellStatus.WALL);
        emptyBoard.setCellStatus(1, 10, CellStatus.WALL);

        emptyBoard.printBoard();
        final int shortestDistance = pathHelper.findShortestDistance(emptyBoard, coordinates1, coordinates2);
        assertThat("Shortest distance to be 4 due to obstacle.", shortestDistance, is(-1));
    }
}
