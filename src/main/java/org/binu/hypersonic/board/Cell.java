package org.binu.hypersonic.board;

/**
 * Each cell on the board
 */
public class Cell {
    CellStatus cellStatus;
    private int numberOfBoxesHit;

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

    public void setNumberOfBoxesHit(int numberOfBoxesHit) {
        this.numberOfBoxesHit = numberOfBoxesHit;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }
}
