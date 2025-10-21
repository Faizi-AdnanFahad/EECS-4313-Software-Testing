package com.github.pedrovgs.problem19;

import com.github.pedrovgs.binarytree.BinaryNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for BinaryTreeDepth class.
 */
public class BinaryTreeDepthTest {

    private BinaryTreeDepth binaryTreeDepth;

    @Before
    public void setUp() {
        binaryTreeDepth = new BinaryTreeDepth();
    }

    // --- Tests for the public get(BinaryNode root) method ---

    /**
     * Tests the defensive check: if (root == null) throw new IllegalArgumentException(...)
     * This hits the first missed branch (the defensive check).
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfRootIsNull() {
        binaryTreeDepth.get(null);
    }

    // --- Tests for the private getInner(BinaryNode root) method ---

    /**
     * Tests the base case: a tree with only a root node.
     * This ensures both get() and getInner() are called and the depth is 1.
     */
    @Test
    public void shouldReturnOneForASingleRootNode() {
        BinaryNode<Integer> root = new BinaryNode<>(10);
        assertEquals(1, binaryTreeDepth.get(root));
    }

    /**
     * Tests the recursive logic with a balanced tree.
     * Tree: R -> L, R -> R. Depth = 2.
     */
    @Test
    public void shouldReturnTwoForABalancedTreeOfDepthTwo() {
        BinaryNode<Integer> root = new BinaryNode<>(10);
        root.setLeft(new BinaryNode<>(5));
        root.setRight(new BinaryNode<>(15));
        
        assertEquals(2, binaryTreeDepth.get(root));
    }

    /**
     * Tests the left-heavy path. Ensures Math.max() selects the left depth.
     * Tree: R -> L -> L. Depth = 3.
     */
    @Test
    public void shouldReturnCorrectDepthForLeftHeavyTree() {
        BinaryNode<Integer> root = new BinaryNode<>(10);
        BinaryNode<Integer> n1 = new BinaryNode<>(5);
        BinaryNode<Integer> n2 = new BinaryNode<>(2);

        root.setLeft(n1);
        n1.setLeft(n2);
        root.setRight(new BinaryNode<>(15)); // Shorter right branch

        assertEquals(3, binaryTreeDepth.get(root));
    }

    /**
     * Tests the right-heavy path. Ensures Math.max() selects the right depth.
     * Tree: R -> R -> R. Depth = 3.
     */
    @Test
    public void shouldReturnCorrectDepthForRightHeavyTree() {
        BinaryNode<Integer> root = new BinaryNode<>(10);
        BinaryNode<Integer> n1 = new BinaryNode<>(15);
        BinaryNode<Integer> n2 = new BinaryNode<>(20);

        root.setRight(n1);
        n1.setRight(n2);
        root.setLeft(new BinaryNode<>(5)); // Shorter left branch

        assertEquals(3, binaryTreeDepth.get(root));
    }
}