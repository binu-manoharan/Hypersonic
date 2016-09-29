package org.binu.hypersonic;

import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.board.Cell;
import org.binu.hypersonic.board.CellStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all hotspots
 */
public class HotSpotProvider {
    private final Board board;
    private List<HotSpot> allHotSpots;

    public HotSpotProvider(Board board) {
        this.board = board;
        allHotSpots = new ArrayList<>();
    }

    public List<HotSpot> getAllHotSpots(int range) {
        final Cell[][] cells = board.getCells();
        for (int y = 0; y < Board.BOARD_HEIGHT; y++) {
            for (int x = 0; x < Board.BOARD_WIDTH; x++) {
                final int numberOfBoxesHit = numberOfBoxesHit(y, x, y, x, range);
                cells[y][x].setNumberOfBoxesHit(numberOfBoxesHit);
                if (numberOfBoxesHit > 0) {
                    allHotSpots.add(new HotSpot(x, y, numberOfBoxesHit));
                    System.err.println("Spot: [" + x + ", " + y + "] = " + numberOfBoxesHit);
                }
            }
        }
        return allHotSpots;
    }

    public int numberOfBoxesHit(int initialY, int initialX, int y, int x, int range) {
        int boxesHit = 0;

        if (y < 0 || y >= Board.BOARD_HEIGHT || x < 0 || x >= Board.BOARD_WIDTH) {
            return 0;
        }

        final boolean rowRange = Math.abs(initialY - y) == range;
        final boolean colRange = Math.abs(initialX - x) == range;
        if (rowRange || colRange) {
            return 0;
        }

        if (board.getCell(x, y).getCellStatus() == CellStatus.BOX && (y != initialY || x != initialX)) {
            return 1;
        }

        if (initialX == x) {
            if (y <= initialY)
                boxesHit += numberOfBoxesHit(initialY, initialX, y - 1, x, range);
            if (y >= initialY)
                boxesHit += numberOfBoxesHit(initialY, initialX, y + 1, x, range);
        }

        if (initialY == y) {
            if (x <= initialX)
                boxesHit += numberOfBoxesHit(initialY, initialX, y, x - 1, range);
            if (x >= initialX)
                boxesHit += numberOfBoxesHit(initialY, initialX, y, x + 1, range);
        }
        return boxesHit;
    }
}
