package org.binu.hypersonic;

import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.board.Cell;
import org.binu.hypersonic.board.CellItem;
import org.binu.hypersonic.board.CellStatus;
import org.binu.hypersonic.entity.Bomb;
import org.binu.hypersonic.entity.EntityHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.binu.hypersonic.TestHelper.getEmptyBoard;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test for {@link HotSpotProvider}
 */
public class HotSpotProviderTest {
    private Board board;
    private HotSpotProvider hotSpotProvider;

    @Before
    public void setUp() throws Exception {
        board = getEmptyBoard();

        board.setCellStatus(3, 0, CellStatus.BOX);
        hotSpotProvider = new HotSpotProvider(board);
    }

    @Test
    public void should_provide_list_of_all_hotspots() throws Exception {
        final List<HotSpot> allHotSpots = hotSpotProvider.getAllHotSpots(3);
        assertThat("There should be 6 hot spots given the range of 3", allHotSpots.size(), is(6));
    }

    @Test
    public void should_give_1_as_number_of_boxes_hit_from_1_0() throws Exception {
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(1, 0, 1, 0, 3);
        assertThat("Should hit 1 box", boxesHit, is(1));
    }

    @Test
    public void should_give_1_as_number_of_boxes_hit_from_2_0() throws Exception {
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(2, 0, 2, 0, 3);
        assertThat("Should hit 1 box", boxesHit, is(1));
    }

    @Test
    public void should_give_0_as_number_of_boxes_hit_from_0_1() throws Exception {
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(0, 1, 0, 1, 3);
        assertThat("Should hit no boxes", boxesHit, is(0));
    }

    @Test
    public void should_give_0_as_number_of_boxes_hit_from_0_2() throws Exception {
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(0, 2, 0, 2, 3);
        assertThat("Should hit no boxes", boxesHit, is(0));
    }

    @Test
    public void should_give_0_as_number_of_boxes_hit_from_0_3() throws Exception {
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(0, 3, 0, 3, 3);
        assertThat("Should hit no boxes", boxesHit, is(0));
    }

    @Test
    public void should_give_0_as_number_of_boxes_hit_from_3_0() throws Exception {
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(3, 0, 3, 0, 3);
        assertThat("Should hit no boxes", boxesHit, is(0));
    }

    @Test
    public void should_give_1_as_number_of_boxes_hit_from_4_0() throws Exception {
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(4, 0, 4, 0, 3);
        assertThat("Should hit 1 box", boxesHit, is(1));
    }

    @Test
    public void should_give_1_as_number_of_boxes_hit_from_5_0() throws Exception {
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(5, 0, 5, 0, 3);
        assertThat("Should hit 1 box", boxesHit, is(1));
    }

    @Test
    public void should_give_0_as_number_of_boxes_hit_from_6_0() throws Exception {
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(6, 0, 6, 0, 3);
        assertThat("Should hit no boxes", boxesHit, is(0));
    }

    @Test
    public void should_give_1_as_number_of_boxes_hit_from_3_1() throws Exception {
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(3, 1, 3, 1, 3);
        assertThat("Should hit 1 box", boxesHit, is(1));
    }

    @Test
    public void should_give_1_as_number_of_boxes_hit_from_3_2() throws Exception {
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(3, 2, 3, 2, 3);
        assertThat("Should hit 1 box", boxesHit, is(1));
    }

    @Test
    public void should_give_0_as_number_of_boxes_hit_from_3_3() throws Exception {
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(3, 3, 3, 3, 3);
        assertThat("Should hit no boxes", boxesHit, is(0));
    }

    @Test
    public void should_give_0_as_number_of_boxes_hit_from_2_1() throws Exception {
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(2, 1, 2, 1, 3);
        assertThat("Should hit no boxes", boxesHit, is(0));
    }

    @Test
    public void should_give_0_as_number_of_boxes_hit_from_4_1() throws Exception {
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(4, 1, 4, 1, 3);
        assertThat("Should hit no boxes", boxesHit, is(0));
    }

    @Test
    public void should_find_14_hotspots_and_0_0_is_not_one_of_them() throws Exception {
        board.setCellStatus(0, 3, CellStatus.BOX);
        board.setCellStatus(1, 1, CellStatus.BOX);
        hotSpotProvider = new HotSpotProvider(board);
        final List<HotSpot> allHotSpots = hotSpotProvider.getAllHotSpots(3);
        assertThat("There should be 14 hotspots", allHotSpots.size(), is(14));
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(0, 0, 0, 0, 3);
        assertThat("Should hit no boxes", boxesHit, is(0));
    }

    @Test
    public void should_hit_four_boxes() throws Exception {
        board.setCellStatus(4, 1, CellStatus.BOX);
        board.setCellStatus(2, 1, CellStatus.BOX);
        board.setCellStatus(3, 2, CellStatus.BOX);
        hotSpotProvider = new HotSpotProvider(board);
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(3, 1, 3, 1, 3);
        assertThat("Should hit 4 boxes", boxesHit, is(4));
    }

    @Test
    public void should_not_have_a_hotspot_at_0_0_as_item_obstructs() throws Exception {
        final Cell cell = board.getCell(2, 0);
        cell.setCellItem(CellItem.BONUS_RANGE);
        hotSpotProvider = new HotSpotProvider(board);
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(1, 0, 1, 0, 3);
        assertThat("Should hit no boxes", boxesHit, is(0));
        final List<HotSpot> allHotSpots = hotSpotProvider.getAllHotSpots(3);
        assertThat("There should only be 5 hotspots", allHotSpots.size(), is(5));
    }

    @Test
    public void should_not_have_a_hotspot_at_0_0_as_bomb_obstructs() throws Exception {
        final EntityHelper entityHelper = new EntityHelper();
        final ArrayList<Bomb> bombs = new ArrayList<>();
        final Bomb bomb = (Bomb) entityHelper.createEntity(1, 2, new Coordinates(2, 0), 7, 3);
        bombs.add(bomb);
        board.addBombs(bombs);

        board.setCellStatus(3, 0, CellStatus.BOX);
        board.boardString();
        hotSpotProvider = new HotSpotProvider(board);
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(1, 0, 1, 0, 3);
        assertThat("Should hit no boxes", boxesHit, is(0));
        final List<HotSpot> allHotSpots = hotSpotProvider.getAllHotSpots(3);
        assertThat("There should only be 4 hotspots", allHotSpots.size(), is(4));
    }

    @Test
    public void should_not_have_a_hotspot_at_0_0_as_wall_obstructs() throws Exception {
        board.setCellStatus(2, 0, CellStatus.WALL);
        hotSpotProvider = new HotSpotProvider(board);
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(1, 0, 1, 0, 3);
        assertThat("Should hit no boxes", boxesHit, is(0));
        final List<HotSpot> allHotSpots = hotSpotProvider.getAllHotSpots(3);
        assertThat("There should only be 4 hotspots", allHotSpots.size(), is(4));
    }
}
