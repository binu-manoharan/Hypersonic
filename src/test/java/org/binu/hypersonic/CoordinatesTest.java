package org.binu.hypersonic;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Test for {@link Coordinates}
 */
public class CoordinatesTest {

    @Test
    public void should_provide_no_coordinates_around_out_of_map_coordinates() throws Exception {
        final Coordinates coordinates = new Coordinates(-1, 0);
        final List coordinatesAround = coordinates.getCoordinatesAround();
        assertThat("There are no valid coordinates around -1, 0", coordinatesAround.size(), is(0));
    }

    @Test
    public void should_provide_2_coordinates_around_0_0() throws Exception {
        final Coordinates coordinates = new Coordinates(0, 0);
        final List coordinatesAround = coordinates.getCoordinatesAround();
        assertThat("There are 2 valid coordinates around 0, 0", coordinatesAround.size(), is(2));
        assertThat("list contains 1,0", coordinatesAround.contains(new Coordinates(1, 0)), is(true));
        assertThat("list contains 0,1", coordinatesAround.contains(new Coordinates(0, 1)), is(true));
    }

    @Test
    public void should_provide_4_coordinates_around_1_1() throws Exception {
        final Coordinates coordinates = new Coordinates(1, 1);
        final List coordinatesAround = coordinates.getCoordinatesAround();
        assertThat("There are 4 valid coordinates around 1, 1", coordinatesAround.size(), is(4));
        assertThat("list contains 2,1", coordinatesAround.contains(new Coordinates(2, 1)), is(true));
        assertThat("list contains 0,1", coordinatesAround.contains(new Coordinates(0, 1)), is(true));
        assertThat("list contains 1,0", coordinatesAround.contains(new Coordinates(1, 0)), is(true));
        assertThat("list contains 1,2", coordinatesAround.contains(new Coordinates(1, 2)), is(true));
    }
}
