package org.binu.hypersonic.board;

import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.entity.Bomb;

import java.util.ArrayList;

/**
 * Helper to apply heat on the board
 */
public class HeatApplicator {
    private Board board;
    private Cell[][] cells;
    private ArrayList<Bomb> bombs;

    public HeatApplicator() {
        bombs = new ArrayList<>();
    }

    public void applyHeat(Board board) {
        this.board = board;
        cells = board.getCells();
        for (Bomb bomb : bombs) {
            applyHeat(bomb);
        }
    }

    private void applyHeat(Bomb bomb) {
        final int heat = bomb.getHeat();
        final int range = bomb.getRange();
        final Coordinates bombCoordinates = bomb.getCoordinates();
        applyHeat(bombCoordinates, bombCoordinates, heat, range);
    }

    private void applyHeat(Coordinates initialBombCoordinates, Coordinates bombCoordinates, int heat, int range) {
        if (!bombCoordinates.isValidCoordinate()) {
            return;
        }

        final int xDiff = initialBombCoordinates.x - bombCoordinates.x;
        final int yDiff = initialBombCoordinates.y - bombCoordinates.y;
        if (Math.abs(xDiff) == range || Math.abs(yDiff) == range) {
            return;
        }

        final Cell currentCell = cells[bombCoordinates.y][bombCoordinates.x];
        final CellStatus cellStatus = currentCell.getCellStatus();
        if (cellStatus == CellStatus.WALL || cellStatus == CellStatus.BOX) {
            return;
        }

        if (cellStatus == CellStatus.BOMB) {
            final int bombIndex = bombs.indexOf(new Bomb(-1, bombCoordinates, 0, 0));
            final Bomb foundBomb = bombs.get(bombIndex);

            assert foundBomb != null;
            if (foundBomb.getHeat() > heat) {
                foundBomb.setHeat(heat);
                final int initialBombIndex = bombs.indexOf(new Bomb(-1, initialBombCoordinates, 0, 0));
                if (initialBombIndex > bombIndex) {
                    applyHeat(foundBomb);
                }
            }
        }

        final int currentCellHeat = currentCell.getHeat();

        if (currentCellHeat == -1 || currentCellHeat > heat) {
            currentCell.setHeat(heat);
        }

        Coordinates nextCoordinates;

        if (initialBombCoordinates.x == bombCoordinates.x) {
            if (bombCoordinates.y <= initialBombCoordinates.y) {
                nextCoordinates = new Coordinates(bombCoordinates.x, bombCoordinates.y - 1);
                applyHeat(initialBombCoordinates, nextCoordinates, heat, range);
            }
            if (bombCoordinates.y >= initialBombCoordinates.y) {
                nextCoordinates = new Coordinates(bombCoordinates.x, bombCoordinates.y + 1);
                applyHeat(initialBombCoordinates, nextCoordinates, heat, range);
            }
        }

        if (initialBombCoordinates.y == bombCoordinates.y) {
            if (bombCoordinates.x <= initialBombCoordinates.x) {
                nextCoordinates = new Coordinates(bombCoordinates.x - 1, bombCoordinates.y);
                applyHeat(initialBombCoordinates, nextCoordinates, heat, range);
            }
            if (bombCoordinates.x >= initialBombCoordinates.x) {
                nextCoordinates = new Coordinates(bombCoordinates.x + 1, bombCoordinates.y);
                applyHeat(initialBombCoordinates, nextCoordinates, heat, range);
            }
        }
    }

    public void setBombs(ArrayList<Bomb> bombs) {
        this.bombs = bombs;
    }

    public void addBomb(Bomb bomb) {
        bombs.add(bomb);
    }
}
