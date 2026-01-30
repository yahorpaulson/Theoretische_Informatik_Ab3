package ab3;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ab3.impl.Siarheyeu.Ab3Impl;

public class Ab3Tests {
    
    private static Ab3 test = new Ab3Impl();

    // Ein-Knoten-Graph
    private static boolean[][] graph1 = new boolean[][] { { false } };

    // Clique mit drei Knoten
    private static boolean[][] graph2 = new boolean[][] {
		{ false, true,  true  },
		{ true,  false, true  },
		{ true,  true,  false }
    };

    // Pfad mit drei Knoten
    private static boolean[][] graph3 = new boolean[][] {
		{ false, true,  false },
		{ true,  false, true  },
		{ false, true,  false }
    };

    // Kreis mit vier Knoten
    private static boolean[][] graph4 = new boolean[][] {
		{ false, true,  false, true  },
		{ true,  false, true,  false },
		{ false, true,  false, true  },
		{ true,  false, true,  false }
    };


    ///////////////////////////////////////////////////////////////////////////
    // Beginn der Tests
    ///////////////////////////////////////////////////////////////////////////
    
    @Test
    public void testExact() {

	    TreeDecomposition td = test.decomposeExact(graph1);
	    assertTrue(test.isValid(td));
	    assertEquals(0, test.getWidth(td));

	    td = test.decomposeExact(graph2);
	    assertTrue(test.isValid(td));
	    assertEquals(2, test.getWidth(td));

	    td = test.decomposeExact(graph3);
	    assertTrue(test.isValid(td));
	    assertEquals(1, test.getWidth(td));

	    td = test.decomposeExact(graph4);
	    assertTrue(test.isValid(td));
	    assertEquals(2, test.getWidth(td));
    }

    @Test
    public void testFast() {

	    TreeDecomposition td = test.decomposeFast(graph1);
	    assertTrue(test.isValid(td));
	    assertEquals(0, test.getWidth(td));

	    td = test.decomposeFast(graph2);
	    assertTrue(test.isValid(td));
	    assertTrue(2 <= test.getWidth(td));
	    assertTrue(2 >= test.getWidth(td));

	    td = test.decomposeFast(graph3);
	    assertTrue(test.isValid(td));
	    assertTrue(1 <= test.getWidth(td));
	    assertTrue(2 >= test.getWidth(td));

	    td = test.decomposeFast(graph4);
	    assertTrue(test.isValid(td));
	    assertTrue(2 <= test.getWidth(td));
	    assertTrue(3 >= test.getWidth(td));
    }

    @Test
    public void testRandom() {

	    TreeDecomposition td = test.decomposeRandom(graph1, 5);
	    assertTrue(test.isValid(td));
	    assertEquals(0, test.getWidth(td));

	    td = test.decomposeRandom(graph2, 5);
	    assertTrue(test.isValid(td));
	    assertTrue(2 <= test.getWidth(td));
	    assertTrue(2 >= test.getWidth(td));

	    td = test.decomposeRandom(graph3, 5);
	    assertTrue(test.isValid(td));
	    assertTrue(1 <= test.getWidth(td));
	    assertTrue(2 >= test.getWidth(td));

	    td = test.decomposeRandom(graph4, 5);
	    assertTrue(test.isValid(td));
	    assertTrue(2 <= test.getWidth(td));
	    assertTrue(3 >= test.getWidth(td));
    }

    @Test
    public void testTrivial() {

	    TreeDecomposition td = test.decomposeTrivial(graph1);
	    assertTrue(test.isValid(td));
	    assertEquals(0, test.getWidth(td));

	    td = test.decomposeTrivial(graph2);
	    assertTrue(test.isValid(td));
	    assertEquals(2, test.getWidth(td));

	    td = test.decomposeTrivial(graph3);
	    assertTrue(test.isValid(td));
	    assertEquals(2, test.getWidth(td));

	    td = test.decomposeTrivial(graph4);
	    assertTrue(test.isValid(td));
	    assertEquals(3, test.getWidth(td));
    }

    @Test
    public void testIsValid() {

	    TreeNode node = new TreeNode();
	    node.bag = new HashSet<>(Arrays.asList(0, 1));
	    node.children = new HashSet<>();
	    node.parent = null;
	    TreeDecomposition td = new TreeDecomposition(graph2, node);

	    assertFalse(test.isValid(td));

	    node = new TreeNode();
	    node.bag = new HashSet<>(Arrays.asList(0));
	    node.children = new HashSet<>();
	    node.parent = null;
	    td = new TreeDecomposition(graph1, node);

	    assertTrue(test.isValid(td));
    }

    @Test
    public void testDecisionProblem() {

	    assertTrue(test.hasTreewidthBound(graph1, 1));

	    assertTrue(test.hasTreewidthBound(graph2, 2));
	    assertFalse(test.hasTreewidthBound(graph2, 1));

	    assertTrue(test.hasTreewidthBound(graph3, 1));

	    assertTrue(test.hasTreewidthBound(graph4, 2));
	    assertFalse(test.hasTreewidthBound(graph4, 1));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Ende der Tests
    ///////////////////////////////////////////////////////////////////////////
}
