package org.binu.hypersonic;

import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.entity.Bomb;
import org.binu.hypersonic.entity.Bomber;
import org.binu.hypersonic.entity.Item;
import org.binu.hypersonic.move.BombXY;
import org.binu.hypersonic.move.BomberMove;
import org.binu.hypersonic.move.MoveXY;
import org.binu.hypersonic.pathing.PathHelper;

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

    public MadBomber(Bomber myBomber, Board board, List<Bomber> bombers, List<Bomb> bombs, List<Item> items) {
        this.myBomber = myBomber;
        this.board = board;
        this.bombers = bombers;
        this.bombs = bombs;
        this.items = items;
        pathHelper = new PathHelper();
    }

    public BomberMove calculateNextMove() {
        hotSpotProvider = new HotSpotProvider(board);
        final List<HotSpot> allHotSpots = hotSpotProvider.getAllHotSpots(myBomber.getRange());
        final List<HotSpot> reachableHotSpots = getReachableHotSpots(allHotSpots);
        return getBestReachableMove(reachableHotSpots);
    }

    private BomberMove getBestReachableMove(List<HotSpot> reachableHotSpots) {
        if (reachableHotSpots.size() > 0) {
            Collections.sort(reachableHotSpots);
            final Coordinates firstCoordinate = reachableHotSpots.get(0).getCoordinates();
            final Coordinates myCurrentLocation = myBomber.getCurrentLocation();
            if (myCurrentLocation.equals(firstCoordinate)) {
                return new BombXY(firstCoordinate);
            }
            return new MoveXY(firstCoordinate);
        }
        return new MoveXY(myBomber.getCurrentLocation());
    }

    private List<HotSpot> getReachableHotSpots(List<HotSpot> allHotSpots) {
        final List<HotSpot> reachableHotSpots = new ArrayList<>();
        for (HotSpot hotSpot : allHotSpots) {
            final int distanceToHotSpot = pathHelper.findShortestDistance(board, myBomber.getCurrentLocation(), hotSpot.getCoordinates());
            if (distanceToHotSpot > -1) {
                reachableHotSpots.add(hotSpot);
            }
        }
        return reachableHotSpots;
    }
}
