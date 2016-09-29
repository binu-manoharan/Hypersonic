package org.binu.hypersonic.board;

/**
 * Each cell on the board
 */
public class Cell {
    CellStatus cellStatus;

    public Cell(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    String getCellPrint() {
        if (cellStatus == CellStatus.EMPTY)
            return "X";
        else if (cellStatus == CellStatus.BOX)
            return "B";
        else
            return "*";

    }
}
