package org.binu.hypersonic.tree;

import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.TestHelper;
import org.binu.hypersonic.entity.Bomber;
import org.binu.hypersonic.entity.EntityHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Test for {@link TreeNode}
 */
public class TreeNodeTest {

    private TreeNode treeNode;
    private Bomber bomber;

    @Before
    public void setUp() throws Exception {
        bomber = (Bomber) new EntityHelper().createEntity(0, 0, new Coordinates(0, 0), 1, 3);
        treeNode = new TreeNode(TestHelper.getEmptyBoard(), bomber);
    }

    @Test
    public void should_get_valid_moves_for_bomber() throws Exception {
        final List availableMoves = treeNode.getAvailableMoves();
        assertThat("Bomber has moves.", availableMoves, is(notNullValue()));
        assertThat("Bomber has 4 moves.", availableMoves.size(), is(4));
    }
}
