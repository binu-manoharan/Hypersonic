package org.binu.hypersonic;

import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.board.BoardHelper;

/**
 * Helper method for test classes.
 */
public class TestHelper {
    static BoardHelper boardHelper = new BoardHelper();

    public static String getEmptyRowString() {
        return ".............";
    }

    public static String[] getEmptyBoardString() {
        String[] board = {
                ".............",
                ".............",
                ".............",
                ".............",
                ".............",
                ".............",
                ".............",
                ".............",
                ".............",
                ".............",
                "............."
        };
        return board;
    }

    public static Board getEmptyBoard() {
        return boardHelper.convertBoard(getEmptyBoardString());
    }
}
