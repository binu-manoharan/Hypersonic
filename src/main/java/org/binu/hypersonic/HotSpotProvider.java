package org.binu.hypersonic;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all hotspots
 */
public class HotSpotProvider {
    private final char[][] board;
    private List<HotSpot> allHotSpots;

    public HotSpotProvider(char[][] board) {
        this.board = board;
        allHotSpots = new ArrayList<>();
    }

    public List<HotSpot> getAllHotSpots(int range) {

        for (int x = 0; x < BoardHelper.BOARD_WIDTH; x++) {
            for (int y = 0; y < BoardHelper.BOARD_HEIGHT; y++) {
                if (board[x][y] == BoardHelper.EMPTY_FLOOR) {
                    final int numberOfBoxesHit = numberOfBoxesHit(x, y, x, y, range);
                    if (numberOfBoxesHit > 0) {
                        allHotSpots.add(new HotSpot(x, y, numberOfBoxesHit));
                        System.err.println("Spot: [" + x + ", " + y + "] = " + numberOfBoxesHit);
                    }
                }
            }
        }
        return allHotSpots;
    }

    public int numberOfBoxesHit(int initialX, int initialY, int x, int y, int range) {
        int boxesHit = 0;

        if (x < 0 || x >= BoardHelper.BOARD_WIDTH || y < 0 || y >= BoardHelper.BOARD_HEIGHT) {
            return 0;
        }

        final boolean rowRange = Math.abs(initialX - x) == range;
        final boolean colRange = Math.abs(initialY - y) == range;
        if (rowRange || colRange) {
            return 0;
        }

        if (board[x][y] == BoardHelper.BOX && (x != initialX || y != initialY)) {
            return 1;
        }

        if (initialY == y) {
            if (x <= initialX)
                boxesHit += numberOfBoxesHit(initialX, initialY, x - 1, y, range);
            if (x >= initialX)
                boxesHit += numberOfBoxesHit(initialX, initialY, x + 1, y, range);
        }

        if (initialX == x) {
            if (y <= initialY)
                boxesHit += numberOfBoxesHit(initialX, initialY, x, y - 1, range);
            if (y >= initialY)
                boxesHit += numberOfBoxesHit(initialX, initialY, x, y + 1, range);
        }
        return boxesHit;
    }
}
