package org.binu.hypersonic;

/**
 * Helper class to process row data in string to int array
 */
public class BoardHelper {

    private static final int BOARD_WIDTH = 13;
    private static final int BOARD_HEIGHT = 11;

    public char[] convertRow(String rowString) {
        char[] row = new char[BOARD_WIDTH];
        assert BOARD_WIDTH == rowString.length();

        for (int index = 0; index < BOARD_WIDTH; index++) {
            final char charAtIndex = rowString.charAt(index);
            switch (charAtIndex) {
                case '.':
                    row[index] = '.';
                    break;
                case '0':
                    row[index] = '0';
                    break;
                default:
                    assert false: "unhandled char in row string";
            }
        }
        return row;
    }

    public char[][] convertBoard(String[] boardString) {
        char[][] board = new char[BOARD_HEIGHT][];

        assert BOARD_HEIGHT == boardString.length;

        for (int rowIndex = 0; rowIndex < BOARD_HEIGHT; rowIndex++) {
            board[rowIndex] = convertRow(boardString[rowIndex]);
        }
        return board;
    }
}
