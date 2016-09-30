package org.binu.hypersonic.board;

import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.entity.Bomb;
import org.binu.hypersonic.entity.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Board containing the game grid and all cells
 */
public class Board {
    public static final int BOARD_WIDTH = 13;
    public static final int BOARD_HEIGHT = 11;

    private final Cell[][] cells;
    private HeatApplicator heatApplicator;

    public Board() {
        cells = new Cell[BOARD_HEIGHT][BOARD_WIDTH];
        heatApplicator = new HeatApplicator();
    }

    void setRow(int rowIndex, Cell[] rowCells) {
        this.cells[rowIndex] = rowCells;
    }

    public Cell[] getRow(int rowIndex) {
        return cells[rowIndex];
    }

    public void printBoard() {
        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                System.err.print(cells[y][x].getCellPrint());
            }
            System.err.println("");
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public Cell getCell(int x, int y) {
        return cells[y][x];
    }

    public void setCellStatus(int x, int y, CellStatus cellStatus) {
        cells[y][x].setCellStatus(cellStatus);
    }

    public CellStatus getCellStatus(Coordinates coordinates) {
        return cells[coordinates.y][coordinates.x].getCellStatus();
    }

    public void addBombs(ArrayList<Bomb> bombs) {
        heatApplicator.setBombs(bombs);

        for (Bomb bomb : bombs) {
            final Coordinates currentLocation = bomb.getCoordinates();
            cells[currentLocation.y][currentLocation.x].setCellStatus(CellStatus.BOMB);
        }
    }

    /**
     * This is for the items outside the box
     *
     * @param items list of items dropped on the floor
     */
    public void addItems(ArrayList<Item> items) {
        for (Item item : items) {
            final Coordinates currentLocation = item.getCoordinates();
            cells[currentLocation.y][currentLocation.x].setCellItem(item.getItemType());
        }
    }

    public ArrayList<Coordinates> getValidMoves(Coordinates coordinates) {
        final ArrayList<Coordinates> validMoves = new ArrayList<>();
        final List<Coordinates> coordinatesAround = coordinates.getCoordinatesAround();
        for (Coordinates possibleCoordinateToMove : coordinatesAround) {
            if (coordinateIsMovable(possibleCoordinateToMove)) {
                validMoves.add(possibleCoordinateToMove);
            }
        }
        return validMoves;
    }

    private Cell getCell(Coordinates coordinates) {
        return cells[coordinates.y][coordinates.x];
    }

    public boolean coordinateIsMovable(Coordinates coordinates) {
        return getCell(coordinates).getCellStatus() == CellStatus.EMPTY;
    }

    public void calculateHeat() {
        heatApplicator.applyHeat(this);
    }

    public void addBomb(Bomb bomb) {
        heatApplicator.addBomb(bomb);

        final Coordinates bombCoordinates = bomb.getCoordinates();
        cells[bombCoordinates.y][bombCoordinates.x].setCellStatus(CellStatus.BOMB);
    }
}
