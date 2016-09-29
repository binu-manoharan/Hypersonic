package org.binu.hypersonic.board;

/**
 * Helper class to process row data in string to int array
 */
public class BoardHelper {

    public static final char EMPTY_FLOOR = '.';
    public static final char BOX = '0';

    public Cell[] convertRow(String rowString) {
        Cell[] row = new Cell[Board.BOARD_WIDTH];
        assert Board.BOARD_WIDTH == rowString.length();

        for (int index = 0; index < Board.BOARD_WIDTH; index++) {
            final char charAtIndex = rowString.charAt(index);
            switch (charAtIndex) {
                case EMPTY_FLOOR:
                    row[index] = new Cell(CellStatus.EMPTY);
                    break;
                case BOX:
                    row[index] = new Cell(CellStatus.BOX);
                    break;
                default:
                    assert false : "unhandled char in row string";
            }
        }
        return row;
    }

    public Board convertBoard(String[] boardString) {
        final Board board = new Board();

        assert Board.BOARD_HEIGHT == boardString.length;

        for (int y = 0; y < Board.BOARD_HEIGHT; y++) {
            final Cell[] row = convertRow(boardString[y]);
            board.setRow(y, row);
        }
        return board;
    }
}
