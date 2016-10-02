package org.binu.hypersonic;

import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.board.CellStatus;
import org.binu.hypersonic.entity.Bomber;
import org.binu.hypersonic.entity.EntityHelper;
import org.binu.hypersonic.move.BomberMove;
import org.junit.Before;
import org.junit.Test;

import static java.util.Collections.*;
import static java.util.Collections.singletonList;
import static org.binu.hypersonic.TestHelper.getEmptyBoard;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test for {@link MadBomber}
 */
public class MadBomberTest {

    private Board board;
    private Bomber bomber;
    private EntityHelper entityHelper;

    @Before
    public void setUp() throws Exception {
        board = getEmptyBoard();
        entityHelper = new EntityHelper();
        bomber = (Bomber) entityHelper.createEntity(0, 0, new Coordinates(10, 0), 1, 3);
    }

    @Test
    public void should_try_to_bomb_the_box() throws Exception {
        board.setCellStatus(2, 0, CellStatus.BOX);
        board.setCellStatus(2, 1, CellStatus.WALL);
        board.setCellStatus(0, 2, CellStatus.WALL);
        board.setCellStatus(1, 1, CellStatus.WALL);
        bomber = (Bomber) entityHelper.createEntity(0, 0, new Coordinates(1, 0), 1, 3);
        MadBomber madBomber = new MadBomber(bomber, board, singletonList(bomber), emptyList(), emptyList());
        final BomberMove bomberMove = madBomber.calculateNextMove();
        assertThat("Bomber move is not null", bomberMove.render(), is(not(nullValue())));
        assertThat("Bomber move is BOMB [1,0]", bomberMove.render().contains("BOMB"), is(true));
    }

    @Test
    public void should_try_bomb_the_box_at_1_0_as_others_are_unreachable() throws Exception {
        board.setCellStatus(1, 1, CellStatus.WALL);
        board.setCellStatus(2, 0, CellStatus.BOX);
        board.setCellStatus(0, 2, CellStatus.WALL);
        board.setCellStatus(4, 0, CellStatus.BOX);
        board.boardString();

        bomber = (Bomber) entityHelper.createEntity(0, 0, new Coordinates(1, 0), 1, 3);
        MadBomber madBomber = new MadBomber(bomber, board, singletonList(bomber), emptyList(), emptyList());
        final BomberMove bomberMove = madBomber.calculateNextMove();
        assertThat("Bomber move is not null", bomberMove.render(), is(not(nullValue())));
        final String render = bomberMove.render();

        assertThat("Bomber move is BOMB [1,0]", render.equals("BOMB 1 0") || render.equals("BOMB 0 0"), is(true));
    }
}
