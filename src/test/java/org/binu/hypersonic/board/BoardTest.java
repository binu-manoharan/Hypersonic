package org.binu.hypersonic.board;

import org.binu.hypersonic.TestHelper;
import org.junit.Before;
import org.junit.Test;

import static org.binu.hypersonic.TestHelper.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test for {@link Board}
 */
public class BoardTest {

    private Board board;
    private Cell[] rowCells;

    @Before
    public void setUp() throws Exception {
        board = new Board();
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
    public void should_print_an_empty_board() throws Exception {
        board = getEmptyBoard();
        board.printBoard();
    }
}
