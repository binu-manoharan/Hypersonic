package org.binu.hypersonic.entity;

import org.binu.hypersonic.Coordinates;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Test for {@link Bomb}
 */
public class BombTest {
    @Test
    public void should_find_element_in_list() throws Exception {
        final Bomb bomb = new Bomb(0, new Coordinates(0, 0), 1, 2);
        final List<Bomb> bombs = new ArrayList<>();
        bombs.add(bomb);
        assertThat("",bombs.contains(new Bomb(1, new Coordinates(0,0), 5,5)),is(true));
    }
}
