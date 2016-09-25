package org.binu.hypersonic;

import org.binu.hypersonic.entity.Bomber;
import org.binu.hypersonic.entity.EntityHelper;
import org.binu.hypersonic.move.BomberMove;
import org.junit.Before;
import org.junit.Test;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.singletonList;
import static org.binu.hypersonic.TestHelper.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test for {@link MadBomber}
 */
public class MadBomberTest {

    private char[][] emptyBoard;
    private Bomber bomber;
    private EntityHelper entityHelper;

    @Before
    public void setUp() throws Exception {
        emptyBoard = getEmptyBoard();
        entityHelper = new EntityHelper();
        bomber = (Bomber) entityHelper.createEntity(0, 0, new Coordinates(0, 10), 1, 3);
    }

    @Test
    public void should_be_nothing_for_the_bomber_to_do_in_a_totally_empty_board() throws Exception {
        MadBomber madBomber = new MadBomber(bomber, emptyBoard, singletonList(bomber), EMPTY_LIST);
        final BomberMove bomberMove = madBomber.calculateNextMove();
        assertThat("Bomber move is not null", bomberMove.render(), is(not(nullValue())));
        final Coordinates currentLocation = bomber.getCurrentLocation();
        final String expectedMoveString = "MOVE " + currentLocation.x + " " + currentLocation.y;
        assertThat("Bomber move is to move to current location", bomberMove.render(), is(expectedMoveString));
    }

    @Test
    public void should_try_to_move_closer_to_box() throws Exception {
        emptyBoard[0][0] = '0';
        MadBomber madBomber = new MadBomber(bomber, emptyBoard, singletonList(bomber), EMPTY_LIST);
        final BomberMove bomberMove = madBomber.calculateNextMove();
        assertThat("Bomber move is not null", bomberMove.render(), is(not(nullValue())));
        assertThat("Bomber move is move [0,1]", bomberMove.render(), is("MOVE 0 1"));
    }

    @Test
    public void should_try_to_bomb_the_box() throws Exception {
        emptyBoard[0][0] = '0';
        bomber = (Bomber) entityHelper.createEntity(0, 0, new Coordinates(0, 1), 1, 3);
        MadBomber madBomber = new MadBomber(bomber, emptyBoard, singletonList(bomber), EMPTY_LIST);
        final BomberMove bomberMove = madBomber.calculateNextMove();
        assertThat("Bomber move is not null", bomberMove.render(), is(not(nullValue())));
        assertThat("Bomber move is bomb [0,1]", bomberMove.render(), is("BOMB 0 1"));
    }
}
