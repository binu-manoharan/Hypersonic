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

    public Board(Board board) {
        final Cell[][] cells = board.getCells();
        this.cells = new Cell[BOARD_HEIGHT][BOARD_WIDTH];
        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                this.cells[y][x] = new Cell(cells[y][x]);
            }
        }
        heatApplicator = new HeatApplicator(board.getHeatApplicator().getBombs());
    }

    void setRow(int rowIndex, Cell[] rowCells) {
        this.cells[rowIndex] = rowCells;
    }

    public Cell[] getRow(int rowIndex) {
        return cells[rowIndex];
    }

    public String[] boardString() {
        String[] rows = new String[BOARD_HEIGHT];
        for (int y = 0; y < BOARD_HEIGHT; y++) {
            String row = "";
            for (int x = 0; x < BOARD_WIDTH; x++) {
                row += cells[y][x].getCellPrint();
            }
            rows[y] = row;
        }
        return rows;
    }

    public void printBoard() {
        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                System.err.print(cells[y][x].getCellPrint());
            }
            System.err.println("");
        }
    }

    public void printBoardHeat() {
        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                System.err.print(cells[y][x].getHeat());
            }
            System.err.println("");
        }
    }

    public String[] boardHeatString() {
        String[] rows = new String[BOARD_HEIGHT];
        for (int y = 0; y < BOARD_HEIGHT; y++) {
            String row = "";
            for (int x = 0; x < BOARD_WIDTH; x++) {
                row += cells[y][x].getHeat();
            }
            rows[y] = row;
        }
        return rows;
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

    public void addBombs(List<Bomb> bombs) {
        for (Bomb bomb : bombs) {
            final boolean bombAdded = addBomb(bomb);
            assert bombAdded : "Bomb failed to add!";
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

    public Cell getCell(Coordinates coordinates) {
        return cells[coordinates.y][coordinates.x];
    }

    public boolean coordinateIsMovable(Coordinates coordinates) {
        return getCell(coordinates).getCellStatus() == CellStatus.EMPTY;
    }

    public void calculateHeat() {
        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                cells[y][x].setHeat(-1);
            }
        }
        heatApplicator.applyHeat(this);
    }

    public boolean addBomb(Bomb bomb) {
        final List<Bomb> existingBombsList = heatApplicator.getBombs();
        if (!existingBombsList.contains(bomb)) {
            heatApplicator.addBomb(bomb);

            final Coordinates bombCoordinates = bomb.getCoordinates();
            cells[bombCoordinates.y][bombCoordinates.x].setCellStatus(CellStatus.BOMB);
            return true;
        } else {
            return false;
        }

    }

    public HeatApplicator getHeatApplicator() {
        return heatApplicator;
    }

    public void tickBombs() {
        heatApplicator.tickBombs();
    }

    public List<Bomb> removeExpiredBombs() {
        return heatApplicator.removeExpiredBombs();
    }
}
