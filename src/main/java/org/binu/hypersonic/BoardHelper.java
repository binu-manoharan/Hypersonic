package org.binu.hypersonic;

/**
 * Helper class to process row data in string to int array
 */
public class BoardHelper {

    public static final int BOARD_WIDTH = 13;
    public static final int BOARD_HEIGHT = 11;
    public static final char EMPTY_FLOOR = '.';
    public static final char BOX = '0';

    public char[] convertRow(String rowString) {
        char[] row = new char[BOARD_WIDTH];
        assert BOARD_WIDTH == rowString.length();

        for (int index = 0; index < BOARD_WIDTH; index++) {
            final char charAtIndex = rowString.charAt(index);
            switch (charAtIndex) {
                case EMPTY_FLOOR:
                    row[index] = EMPTY_FLOOR;
                    break;
                case BOX:
                    row[index] = BOX;
                    break;
                default:
                    assert false : "unhandled char in row string";
            }
        }
        return row;
    }

    public char[][] convertBoard(String[] boardString) {
        char[][] board = new char[BOARD_WIDTH][BOARD_HEIGHT];

        assert BOARD_HEIGHT == boardString.length;

        for (int y = 0; y < BOARD_HEIGHT; y++) {
            final char[] column = convertRow(boardString[y]);
            for (int x = 0; x < BOARD_WIDTH; x++) {
                board[x][y] = column[x];
            }
        }
        //TODO replace with test
//        System.err.println();
//        for (int y = 0; y < BOARD_HEIGHT; y++) {
//            for (int x = 0; x < BOARD_WIDTH; x++) {
//                System.err.print(board[x][y]);
//            }
//            System.err.println();
//        }

        return board;
    }
}
