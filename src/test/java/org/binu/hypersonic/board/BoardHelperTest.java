package org.binu.hypersonic.board;

import org.junit.Before;
import org.junit.Test;

import static org.binu.hypersonic.TestHelper.*;
import static org.binu.hypersonic.TestHelper.getEmptyRowString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

/**
 * Test for {@link BoardHelper}
 */
public class BoardHelperTest {

    private Cell[] row;
    private BoardHelper boardHelper;

    @Before
    public void setUp() throws Exception {
        boardHelper = new BoardHelper();
        row = boardHelper.convertRow(getEmptyRowString());
    }

    @Test
    public void should_convert_a_string_into_empty_element_array() throws Exception {
        boardHelper = new BoardHelper();

        assertThat("Row has 13 elements.", row.length, is(13));
        for (Cell element : row) {
            assertThat("Element is .", element.getCellStatus(), is(CellStatus.EMPTY));
        }
    }

    @Test
    public void should_convert_a_string_to_alternate_empty_and_box_array() throws Exception {
        row = boardHelper.convertRow(".0.0.0.0.0.0.");
        assertThat("Row has 13 elements.", row.length, is(13));
        boolean odd = true;
        for (Cell element : row) {
            if (odd) {
                assertThat("Element is .", element.getCellStatus(), is(CellStatus.EMPTY));
            } else {
                assertThat("Element is .", element.getCellStatus(), is(CellStatus.BOX));
            }
            odd = !odd;
        }
    }

    @Test
    public void should_convert_a_string_to_all_cell_status_and_items() throws Exception {
        row = boardHelper.convertRow(".012.012.012.");
        assertThat("Row has 13 elements.", row.length, is(13));
        for (int i = 0; i < row.length; i++) {
            Cell element = row[i];
            if (i % 4 == 0) {
                assertThat("Element is .", element.getCellStatus(), is(CellStatus.EMPTY));
                assertThat("Element is .", element.getCellItem(), is(nullValue()));
            } else if ( i % 4 == 1) {
                assertThat("Element is .", element.getCellStatus(), is(CellStatus.BOX));
                assertThat("Element is .", element.getCellItem(), is(nullValue()));
            } else if ( i % 4 == 2) {
                assertThat("Element is .", element.getCellStatus(), is(CellStatus.BOX));
                assertThat("Element is .", element.getCellItem(), is(CellItem.BONUS_RANGE));
            } else if ( i % 4 == 3) {
                assertThat("Element is .", element.getCellStatus(), is(CellStatus.BOX));
                assertThat("Element is .", element.getCellItem(), is(CellItem.BONUS_BOMBS));
            }
        }
    }

    @Test(expected = AssertionError.class)
    public void should_throw_error_when_passed_incorrect_string() throws Exception {
        row = boardHelper.convertRow(".3.0.0.0.0.0.");
    }

    @Test
    public void should_get_empty_board_array() throws Exception {
        final String[] boardString = getEmptyBoardString();

        Board board = boardHelper.convertBoard(boardString);
        final Cell[][] cells = board.getCells();
        assertThat("Board is 11 high", cells.length, is(11));

        for (int y = 0; y < Board.BOARD_HEIGHT; y++) {
            assertThat("Board row is 13 wide", cells[y].length, is(13));
            for (int x = 0; x < Board.BOARD_WIDTH; x++) {
                assertThat("Board cell is empty", cells[y][x].getCellStatus(), is(CellStatus.EMPTY));
            }
        }
        board.boardString();
    }

    @Test
    public void should_get_first_row_with_some_boxes() throws Exception {
        final String[] boardString = getEmptyBoardString();
        boardString[0] = ".0.0.0.0.0.0.";
        Board board = boardHelper.convertBoard(boardString);
        final Cell[][] cells = board.getCells();
        for (int y = 1; y < Board.BOARD_HEIGHT; y++) {
            assertThat("Board row is 13 wide", cells[y].length, is(13));
            for (int x = 0; x < Board.BOARD_WIDTH; x++) {
                assertThat("Board cell is empty", cells[y][x].getCellStatus(), is(CellStatus.EMPTY));
            }
        }
        for (int x = 0; x < Board.BOARD_WIDTH; x++) {
            if (x % 2 == 0)
                assertThat("Board cell is empty", cells[0][x].getCellStatus(), is(CellStatus.EMPTY));
            else
                assertThat("Board is cell is occupied by a box", cells[0][x].getCellStatus(), is(CellStatus.BOX));
        }
        board.boardString();
    }

    @Test(expected = AssertionError.class)
    public void should_throw_error_when_passed_incorrect_board_string() throws Exception {
        final String[] boardString = getEmptyBoardString();
        boardString[0] = "............3";
        boardHelper.convertBoard(boardString);
    }
}
