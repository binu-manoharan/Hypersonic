package org.binu.hypersonic.pathing;

import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.board.CellStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Find the shortest path between two paths
 */
public class PathHelper {
    private ArrayList<PathNode> open;
    private ArrayList<PathNode> closed;

    private PathNode[][] pathNodes;

    public PathHelper() {
        pathNodes = new PathNode[Board.BOARD_HEIGHT][Board.BOARD_WIDTH];
        initPathNodes();

        closed = new ArrayList<>();
        open = new ArrayList<>();
    }

    public int findShortestDistance(Board board, Coordinates startingPoint, Coordinates destination) {
        final List<PathNode> path = findPath(board, startingPoint, destination);
        return path == null ? -1 : path.size();
    }

    private List<PathNode> findPath(Board board, Coordinates startingPoint, Coordinates destination) {
        final PathNode startingPathNode = pathNodes[startingPoint.y][startingPoint.x];
        final PathNode destinationPathNode = pathNodes[destination.y][destination.x];

        if (destinationPathNode.equals(startingPathNode)) {
            return new ArrayList<>();
        }

        startingPathNode.setCost(0);

        open.clear();
        closed.clear();

        open.add(startingPathNode);

        while (open.size() != 0) {
            final PathNode currentNode = open.get(0);

            final Coordinates currentNodeCoordinate = currentNode.getCoordinates();
            if (currentNodeCoordinate.equals(destinationPathNode.getCoordinates())) {
                break;
            }

            open.remove(currentNode);
            closed.add(currentNode);

            final CellStatus currentNodeCellStatus = board.getCellStatus(currentNodeCoordinate);
            if (currentNodeCellStatus != CellStatus.EMPTY) {
                continue;
            }

            for (int y = -1; y < 2; y++) {
                for (int x = -1; x < 2; x++) {
                    if ((y == 0) && (x == 0)) {
                        continue;
                    }

                    if ((y != 0) && (x != 0)) {
                        continue;
                    }

                    int xNew = x + currentNodeCoordinate.x;
                    int yNew = y + currentNodeCoordinate.y;


                    if (isValidLocation(xNew, yNew)) {
                        final int nextStepCost = currentNode.getCost() + 1;
                        final PathNode neighbour = pathNodes[yNew][xNew];

                        if (nextStepCost < neighbour.getCost()) {
                            open.remove(neighbour);
                            closed.remove(neighbour);
                        }

                        if (!open.contains(neighbour) && !closed.contains(neighbour)) {
                            neighbour.setCost(nextStepCost);
                            neighbour.setParent(currentNode);
                            open.add(neighbour);
                        }
                    }
                }
            }
        }


        if (destinationPathNode.getParent() == null) {
            return null;
        }

        PathNode target = destinationPathNode;
        final ArrayList<PathNode> path = new ArrayList<>();
        while (target != startingPathNode) {
            path.add(target);
            target = target.getParent();
        }
        Collections.reverse(path);

        return path;
    }

    private boolean isValidLocation(int x, int y) {
        boolean invalid = x < 0 || y < 0 || x >= Board.BOARD_WIDTH || y >= Board.BOARD_HEIGHT;
        return !invalid;
    }

    private void initPathNodes() {
        for (int y = 0; y < Board.BOARD_HEIGHT; y++) {
            for (int x = 0; x < Board.BOARD_WIDTH; x++) {
                pathNodes[y][x] = new PathNode(new Coordinates(x, y));
            }
        }
    }


}
