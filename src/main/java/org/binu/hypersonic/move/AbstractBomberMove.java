package org.binu.hypersonic.move;

import org.binu.hypersonic.Coordinates;

/**
 * Abstract class to handle moves.
 */
public abstract class AbstractBomberMove implements BomberMove {
    public static final char BOMB_CODE = 'B';
    public static final char MOVE_CODE = 'M';
    protected final Coordinates coordinates;

    public AbstractBomberMove(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractBomberMove)) return false;
        AbstractBomberMove that = (AbstractBomberMove) o;
        return this.render().equals(that.render());
    }

    @Override
    public int hashCode() {
        return render() != null ? render().hashCode() : 0;
    }
}
