package org.binu.hypersonic;

import org.binu.hypersonic.entity.Bomber;
import org.binu.hypersonic.entity.EntityHelper;
import org.binu.hypersonic.move.BomberMove;
import org.junit.Before;
import org.junit.Test;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.singletonList;
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
        emptyBoard = TestHelper.getEmptyBoard();
        entityHelper = new EntityHelper();
        bomber = (Bomber) entityHelper.createEntity(0, 0, new Coordinates(10, 10), 1, 3);
    }

    @Test
    public void there_should_be_nothing_for_the_bomber_to_do_in_a_totally_empty_board() throws Exception {
        MadBomber madBomber = new MadBomber(bomber, emptyBoard, singletonList(bomber), EMPTY_LIST);
        final BomberMove bomberMove = madBomber.calculateNextMove();
        assertThat("Bomber move is not null", bomberMove.render(), is(not(nullValue())));
        assertThat("Bomber move is not null", bomberMove.render(), is("MOVE 10 10"));
    }
}
