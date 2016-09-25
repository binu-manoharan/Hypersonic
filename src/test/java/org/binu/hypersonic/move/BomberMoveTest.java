package org.binu.hypersonic.move;

import org.binu.hypersonic.Coordinates;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Test for {@link BomberMove}
 */
public class BomberMoveTest {
    @Test
    public void should_generate_0_0_move_message() throws Exception {
        final MoveXY moveXY = new MoveXY(new Coordinates(0,0));
        final String moveMessage = moveXY.render();

        assertThat("Should generate Move to [0,0] message", moveMessage, is("MOVE 0 0"));
    }

    @Test
    public void should_generate_0_0_bomb_message() throws Exception {
        final BomberMove bombXY = new BombXY(new Coordinates(0,0));
        final String moveMessage = bombXY.render();

        assertThat("Should generate Bomb [0,0] message", moveMessage, is("BOMB 0 0"));
    }
}
