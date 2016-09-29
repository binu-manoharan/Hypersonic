package org.binu.hypersonic;

import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.board.Cell;
import org.binu.hypersonic.board.CellStatus;

import java.util.ArrayList;
import java.util.List;

import static org.binu.hypersonic.board.Board.BOARD_HEIGHT;
import static org.binu.hypersonic.board.Board.BOARD_WIDTH;

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
        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                final int numberOfBoxesHit = numberOfBoxesHit(x, y, x, y, range);
                cells[y][x].setNumberOfBoxesHit(numberOfBoxesHit);
                if (numberOfBoxesHit > 0) {
                    allHotSpots.add(new HotSpot(x, y, numberOfBoxesHit));
                    System.err.println("Spot: [" + x + ", " + y + "] = " + numberOfBoxesHit);
                }
            }
        }
        return allHotSpots;
    }

    public int numberOfBoxesHit(int initialX, int initialY, int x, int y, int range) {
        int boxesHit = 0;

        if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {
            return 0;
        }

        final boolean rowRange = Math.abs(initialX - x) == range;
        final boolean colRange = Math.abs(initialY - y) == range;
        if (rowRange || colRange) {
            return 0;
        }

        if (board.getCell(x, y).getCellStatus() == CellStatus.BOX && (x != initialX || y != initialY)) {
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
