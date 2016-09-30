package org.binu.hypersonic.entity;

import org.binu.hypersonic.Coordinates;

/**
 * A bomber or a bomb, you wouldn't think they'd have much to share inheritance structure.
 * I guess bomber works closely with a bomb? :o
 */
public abstract class Entity {
    private int ownerId;
    private Coordinates coordinates;
    protected int param1;
    protected int param2;

    public Entity(int ownerId, Coordinates coordinates, int param1, int param2) {
        this.ownerId = ownerId;
        this.coordinates = coordinates;
        this.param1 = param1;
        this.param2 = param2;
    }

    public int getEntityType() {
        if (this instanceof Bomber) {
            return 0;
        } else if (this instanceof Bomb) {
            return 1;
        } else if (this instanceof Item) {
            return 2;
        } else {
            assert false : "Another ghost lurking around in the code.";
            return -1;
        }
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getRange() {
        return param2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;

        Entity entity = (Entity) o;
//        TODO may be this is needed but we shouldn't be comparing different types of entities.
//        if (this.getEntityType() != entity.getEntityType()){
//            return false;
//        }
        return coordinates != null && coordinates.equals(entity.coordinates);

    }

    @Override
    public int hashCode() {
        return coordinates != null ? coordinates.hashCode() : 0;
    }
}
