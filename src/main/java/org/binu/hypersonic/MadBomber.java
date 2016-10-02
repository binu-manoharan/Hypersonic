package org.binu.hypersonic;

import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.entity.Bomb;
import org.binu.hypersonic.entity.Bomber;
import org.binu.hypersonic.entity.Item;
import org.binu.hypersonic.move.BombXY;
import org.binu.hypersonic.move.BomberMove;
import org.binu.hypersonic.move.MoveXY;
import org.binu.hypersonic.pathing.PathHelper;
import org.binu.hypersonic.tree.SimpleTree;
import org.binu.hypersonic.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A person with bombs, who has lost his sanity and sense.
 * This bomber has nothing to lose or fear.
 * Be careful when you ask him for his next move.
 */
public class MadBomber {

    private final Bomber myBomber;
    private final PathHelper pathHelper;
    private Board board;
    private final List<Bomber> bombers;
    private final List<Bomb> bombs;
    private final List<Item> items;
    private HotSpotProvider hotSpotProvider;
    private SimpleTree simpleTree;

    public MadBomber(Bomber myBomber, Board board, List<Bomber> bombers, List<Bomb> bombs, List<Item> items) {
        this.myBomber = myBomber;
        this.board = board;
        this.bombers = bombers;
        this.bombs = bombs;
        this.items = items;
        pathHelper = new PathHelper();
        simpleTree = new SimpleTree();
    }

    public BomberMove calculateNextMove() {
        hotSpotProvider = new HotSpotProvider(board);
        final List<HotSpot> allHotSpots = hotSpotProvider.getAllHotSpots(myBomber.getRange());
        final List<HotSpot> reachableHotSpots = getReachableHotSpots(allHotSpots);
        /**
         * Info for tree
         * board - heat level
         * Reachable hot spots
         * Item spots on board - passed as list
         * Item spots inside box - cellItem & cell status combination
         * my bomber - x, y, range and number of bombs
         */
        final TreeNode bestChild = simpleTree.makeTree(board, myBomber, bombs, items, reachableHotSpots);
        return bestChild.getBomberMove();
    }

    private BomberMove getBestReachableMove(List<HotSpot> reachableHotSpots) {
        //TODO what?
        if (reachableHotSpots.size() > 0) {
            Collections.sort(reachableHotSpots);
            final Coordinates firstCoordinate = reachableHotSpots.get(0).getCoordinates();
            final Coordinates myCurrentLocation = myBomber.getCoordinates();
            if (myCurrentLocation.equals(firstCoordinate)) {
                return new BombXY(firstCoordinate, firstCoordinate);
            }
            return new MoveXY(firstCoordinate);
        }
        return new MoveXY(myBomber.getCoordinates());
    }

    private List<HotSpot> getReachableHotSpots(List<HotSpot> allHotSpots) {
        final List<HotSpot> reachableHotSpots = new ArrayList<>();
        for (HotSpot hotSpot : allHotSpots) {
            final int distanceToHotSpot = pathHelper.findShortestDistance(board, myBomber.getCoordinates(), hotSpot.getCoordinates());
            if (distanceToHotSpot > -1) {
                reachableHotSpots.add(hotSpot);
            }
        }
        return reachableHotSpots;
    }
}
