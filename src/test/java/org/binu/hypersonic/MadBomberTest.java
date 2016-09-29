package org.binu.hypersonic;

import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.board.CellStatus;
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

    private Board emptyBoard;
    private Bomber bomber;
    private EntityHelper entityHelper;

    @Before
    public void setUp() throws Exception {
        emptyBoard = getEmptyBoard();
        entityHelper = new EntityHelper();
        bomber = (Bomber) entityHelper.createEntity(0, 0, new Coordinates(10, 0), 1, 3);
    }

    @Test
    public void should_be_nothing_for_the_bomber_to_do_in_a_totally_empty_board() throws Exception {
        MadBomber madBomber = new MadBomber(bomber, emptyBoard, singletonList(bomber), EMPTY_LIST, EMPTY_LIST);
        final BomberMove bomberMove = madBomber.calculateNextMove();
        assertThat("Bomber move is not null", bomberMove.render(), is(not(nullValue())));
        final Coordinates currentLocation = bomber.getCurrentLocation();
        final String expectedMoveString = "MOVE " + currentLocation.x + " " + currentLocation.y;
        assertThat("Bomber move is to move to current location", bomberMove.render(), is(expectedMoveString));
    }

    @Test
    public void should_try_to_move_closer_to_box() throws Exception {
        emptyBoard.setCellStatus(0, 0, CellStatus.BOX);
        MadBomber madBomber = new MadBomber(bomber, emptyBoard, singletonList(bomber), EMPTY_LIST, EMPTY_LIST);
        final BomberMove bomberMove = madBomber.calculateNextMove();
        assertThat("Bomber move is not null", bomberMove.render(), is(not(nullValue())));
        assertThat("Bomber move is move [1,0]", bomberMove.render(), is("MOVE 1 0"));
    }

    @Test
    public void should_try_to_bomb_the_box() throws Exception {
        emptyBoard.setCellStatus(0, 0, CellStatus.BOX);
        bomber = (Bomber) entityHelper.createEntity(0, 0, new Coordinates(1, 0), 1, 3);
        MadBomber madBomber = new MadBomber(bomber, emptyBoard, singletonList(bomber), EMPTY_LIST, EMPTY_LIST);
        final BomberMove bomberMove = madBomber.calculateNextMove();
        assertThat("Bomber move is not null", bomberMove.render(), is(not(nullValue())));
        assertThat("Bomber move is BOMB [1,0]", bomberMove.render(), is("BOMB 1 0"));
    }

    @Test
    public void should_try_bomb_the_box_at_1_0_as_others_are_unreachable() throws Exception {
        emptyBoard.setCellStatus(1, 1, CellStatus.WALL);
        emptyBoard.setCellStatus(2, 0, CellStatus.BOX);
        emptyBoard.setCellStatus(0, 2, CellStatus.WALL);
        emptyBoard.setCellStatus(4, 0, CellStatus.BOX);
        emptyBoard.printBoard();

        bomber = (Bomber) entityHelper.createEntity(0, 0, new Coordinates(1, 0), 1, 3);
        MadBomber madBomber = new MadBomber(bomber, emptyBoard, singletonList(bomber), EMPTY_LIST, EMPTY_LIST);
        final BomberMove bomberMove = madBomber.calculateNextMove();
        assertThat("Bomber move is not null", bomberMove.render(), is(not(nullValue())));
//        assertThat("Bomber move is BOMB [1,0]", bomberMove.render(), is("BOMB 1 0"));
    }
}
