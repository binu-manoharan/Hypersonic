package org.binu.hypersonic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Test for {@link BoardHelper}
 */
public class BoardHelperTest {

    private char[] row;
    private BoardHelper boardHelper;

    @Before
    public void setUp() throws Exception {
        boardHelper = new BoardHelper();
        row = boardHelper.convertRow(".............");
    }

    @Test
    public void should_convert_a_string_into_empty_element_array() throws Exception {
        boardHelper = new BoardHelper();

        assertThat("Row has 13 elements.", row.length, is(13));
        for (char element : row) {
            assertThat("Element is .", element, is('.'));
        }
    }

    @Test
    public void should_convert_a_string_to_alternate_empty_and_box_array() throws Exception {
        row = boardHelper.convertRow(".0.0.0.0.0.0.");
        assertThat("Row has 13 elements.", row.length, is(13));
        boolean odd = true;
        for (char element : row) {
            if (odd) {
                assertThat("Element is .", element, is('.'));
            } else {
                assertThat("Element is .", element, is('0'));
            }
            odd = !odd;
        }
    }

    @Test(expected = AssertionError.class)
    public void should_throw_error_when_passed_incorrect_string() throws Exception {
        row = boardHelper.convertRow(".1.0.0.0.0.0.");
    }
}
