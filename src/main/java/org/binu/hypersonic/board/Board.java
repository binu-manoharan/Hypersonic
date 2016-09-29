package org.binu.hypersonic.board;

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

    void setCell(int x, int y, Cell cell) {
        cells[x][y] = cell;
    }

    void setRow(int rowIndex, Cell[] rowCells) {
        this.cells[rowIndex] = rowCells;
    }

    public Cell[] getRow(int index) {
        return cells[index];
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
}
