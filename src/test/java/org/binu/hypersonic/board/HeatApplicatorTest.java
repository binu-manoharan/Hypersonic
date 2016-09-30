package org.binu.hypersonic.board;

import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.entity.Bomb;
import org.binu.hypersonic.entity.EntityHelper;
import org.junit.Before;
import org.junit.Test;

import static org.binu.hypersonic.TestHelper.getEmptyBoard;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test for {@link HeatApplicator}
 */
public class HeatApplicatorTest {
    private static final int RANGE_3 = 3;
    private static final Coordinates COORDINATES_0_0 = new Coordinates(0, 0);
    private static final int HEAT_7 = 7;
    private static final Coordinates COORDINATES_3_3 = new Coordinates(3, 3);
    public static final Coordinates COORDINATES_1_0 = new Coordinates(1, 0);
    public static final int HEAT_5 = 5;

    public void setBoard(Board board) {
        this.board = board;
    }

    private Board board;
    private EntityHelper entityHelper;

    @Before
    public void setUp() throws Exception {
        board = getEmptyBoard();
        entityHelper = new EntityHelper();
    }

    @Test
    public void should_calculate_heat_for_board_at_0_0() throws Exception {
        final Bomb bomb = (Bomb) entityHelper.createEntity(1, 0, COORDINATES_0_0, HEAT_7, RANGE_3);
        board.addBomb(bomb);
        board.calculateHeat();
        assertHeatInRange(COORDINATES_0_0, HEAT_7, RANGE_3);
    }

    @Test
    public void should_calculate_heat_for_board_at_3_3() throws Exception {
        final Bomb bomb = (Bomb) entityHelper.createEntity(1, 0, COORDINATES_3_3, HEAT_7, RANGE_3);
        board.addBomb(bomb);
        board.calculateHeat();
        assertHeatInRange(COORDINATES_3_3, HEAT_7, RANGE_3);
        assertCellHeat(3, 0, -1);
        assertCellHeat(0, 3, -1);
        assertCellHeat(6, 3, -1);
        assertCellHeat(3, 6, -1);
        assertCellHeat(2, 2, -1);
        assertCellHeat(4, 4, -1);
        assertCellHeat(4, 2, -1);
        assertCellHeat(2, 4, -1);
    }

    @Test
    public void should_calculate_heat_for_two_bombs() throws Exception {
        final Bomb bomb1 = (Bomb) entityHelper.createEntity(1, 0, COORDINATES_0_0, HEAT_7, RANGE_3);
        final Bomb bomb2 = (Bomb) entityHelper.createEntity(1, 0, COORDINATES_1_0, HEAT_5, RANGE_3);
        board.addBomb(bomb1);
        board.addBomb(bomb2);
        board.calculateHeat();
        assertHeatInRange(COORDINATES_1_0, HEAT_5, RANGE_3);
        assertHeatInRange(COORDINATES_0_0, HEAT_5, RANGE_3);
    }

    public void assertHeatInRange(Coordinates coordinates, int heat, int range) {
        for (int y = -range + 1; y < range; y++) {
            for (int x = -range + 1; x < range; x++) {
                final int xNew = coordinates.x + x;
                final int yNew = coordinates.y + y;

                if (xNew != coordinates.x && yNew != coordinates.y) {
                    continue;
                }

                if (new Coordinates(xNew, yNew).isValidCoordinate()) {
                    assertCellHeat(xNew, yNew, heat);
                }
            }
        }
    }

    private void assertCellHeat(int x, int y, int heat) {
        System.out.println("heat is set for [" + x + ","+ y +"] to " + heat);
        assertThat("heat is set for [" + x + ","+ y +"] to " + heat, board.getCell(x, y).getHeat(), is(heat));
    }
}
