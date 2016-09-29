package org.binu.hypersonic;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.binu.hypersonic.TestHelper.getEmptyBoard;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test for {@link HotSpotProvider}
 */
public class HotSpotProviderTest {
    private char[][] board;
    private HotSpotProvider hotSpotProvider;

    @Before
    public void setUp() throws Exception {
        board = getEmptyBoard();
        board[3][0] = '0';
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
        board[0][3] = '0';
        board[1][1] = '0';
        hotSpotProvider = new HotSpotProvider(board);
        final List<HotSpot> allHotSpots = hotSpotProvider.getAllHotSpots(3);
        assertThat("There should be 14 hotspots", allHotSpots.size(), is(14));
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(0, 0, 0, 0, 3);
        assertThat("Should hit no boxes", boxesHit, is(0));
    }

    @Test
    public void should_hit_four_boxes() throws Exception {
        board[4][1] = '0';
        board[2][1] = '0';
        board[3][2] = '0';
        hotSpotProvider = new HotSpotProvider(board);
        final int boxesHit = hotSpotProvider.numberOfBoxesHit(3,1, 3, 1, 3);
        assertThat("Should hit 4 boxes", boxesHit, is(4));
    }
}