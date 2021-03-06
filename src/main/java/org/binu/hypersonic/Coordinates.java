package org.binu.hypersonic;

import org.binu.hypersonic.board.Board;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a location on the grid
 */
public class Coordinates {
    public final int x;
    public final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(Coordinates coordinates) {
        this.x = coordinates.x;
        this.y = coordinates.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (x != that.x) return false;
        return y == that.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public List<Coordinates> getCoordinatesAround() {
        final ArrayList<Coordinates> coordinatesAround = new ArrayList<>();

        if (!isValidCoordinate()) {
            return coordinatesAround;
        }

        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if (x == 0 && y == 0) {
                    continue;
                }

                if (x != 0 && y != 0) {
                    continue;
                }

                final int xNew = this.x + x;
                final int yNew = this.y + y;
                final Coordinates newCoordinates = new Coordinates(xNew, yNew);
                if (newCoordinates.isValidCoordinate()) {
                    coordinatesAround.add(newCoordinates);
                }
            }
        }
        return coordinatesAround;
    }

    public boolean isValidCoordinate() {
        return x >= 0 && x < Board.BOARD_WIDTH && y >= 0 && y < Board.BOARD_HEIGHT;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
