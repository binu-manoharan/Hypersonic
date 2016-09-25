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
        bomber = (Bomber) entityHelper.createEntity(0, 0, new Coordinates(0, 0), 1, 3);
    }

    @Test
    public void should_calculate_next_move() throws Exception {
        MadBomber madBomber = new MadBomber(0, emptyBoard, singletonList(bomber), EMPTY_LIST);
        final BomberMove bomberMove = madBomber.calculateNextMove();
        assertThat("Bomber move is not null", bomberMove.render(), is(not(nullValue())));
    }
}
