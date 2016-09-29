package org.binu.hypersonic.pathing;

import org.binu.hypersonic.Coordinates;

/**
 *
 */
public class PathNode {
    private int cost;
    private PathNode parent;
    private Coordinates coordinates;

    public PathNode(Coordinates coordinates) {
        this.coordinates = coordinates;
        cost = 1024;
        parent = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PathNode pathNode = (PathNode) o;

        return coordinates != null ? coordinates.equals(pathNode.coordinates) : pathNode.coordinates == null;
    }

    @Override
    public int hashCode() {
        return coordinates != null ? coordinates.hashCode() : 0;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDepth() {
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setParent(PathNode parent) {
        this.parent = parent;
    }

    public PathNode getParent() {
        return parent;
    }
}
