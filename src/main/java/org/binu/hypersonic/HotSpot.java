package org.binu.hypersonic;

/**
 * A coordinate where there is at least 1 box to blow up
 */
public class HotSpot implements Comparable<HotSpot>{
    private int x, y;
    private int numBoxes;

    public HotSpot(int x, int y, int numBoxes) {
        this.x = x;
        this.y = y;
        this.numBoxes = numBoxes;
    }

    public int getNumBoxes() {
        return numBoxes;
    }

    public Coordinates getCoordinates() {
        return new Coordinates(x, y);
    }

    @Override
    public int compareTo(HotSpot hotSpot) {
        return this.numBoxes > hotSpot.numBoxes ? -1 : this.numBoxes < hotSpot.numBoxes ? 1 : 0;
    }
}
