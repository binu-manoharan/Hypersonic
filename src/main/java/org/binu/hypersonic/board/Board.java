package org.binu.hypersonic.board;

import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.entity.Bomb;

import java.util.ArrayList;

/**
 * Board containing the game grid and all cells
 */
public class Board {
    public static final int BOARD_WIDTH = 13;
    public static final int BOARD_HEIGHT = 11;

    private final Cell[][] cells;

    public Board() {
        cells = new Cell[BOARD_HEIGHT][BOARD_WIDTH];
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
        for (Bomb bomb : bombs) {
            final Coordinates currentLocation = bomb.getCurrentLocation();
            cells[currentLocation.y][currentLocation.x].setCellStatus(CellStatus.BOMB);
        }
    }
}
