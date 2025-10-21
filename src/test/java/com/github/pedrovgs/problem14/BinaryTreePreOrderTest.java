package com.github.pedrovgs.problem14;

import com.github.pedrovgs.binarytree.BinaryNode;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for BinaryTreePreOrder class, covering both recursive and iterative methods.
 */
public class BinaryTreePreOrderTest {

    private BinaryTreePreOrder traversal;

    @Before
    public void setUp() {
        traversal = new BinaryTreePreOrder();
    }

    // --- Helper Methods ---

    /**
     * Creates a standard test tree:
     * 1
     * / \
     * 2   3
     * / \
     * 4   5
     */
    private BinaryNode<Integer> buildStandardTree() {
        BinaryNode<Integer> root = new BinaryNode<>(1);
        BinaryNode<Integer> n2 = new BinaryNode<>(2);
        BinaryNode<Integer> n3 = new BinaryNode<>(3);
        root.setLeft(n2);
        root.setRight(n3);
        n2.setLeft(new BinaryNode<>(4));
        n2.setRight(new BinaryNode<>(5));
        return root;
    }

    /**
     * Extracts data from a List<BinaryNode> for easy comparison.
     */
    private <T> List<T> extractData(List<BinaryNode> nodes) {
        List<T> dataList = new LinkedList<>();
        for (BinaryNode node : nodes) {
            dataList.add((T) node.getData());
        }
        return dataList;
    }

    // --- Tests for Defensive Check (validateBinaryNode) ---

    @Test(expected = IllegalArgumentException.class)
    public void recursive_shouldThrowExceptionForNullRoot() {
        traversal.getRecursive(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void iterative_shouldThrowExceptionForNullRoot() {
        traversal.getIterative(null);
    }

    // --- Tests for getRecursive(BinaryNode) and getInner(BinaryNode) ---

    /**
     * Tests the base case: one node.
     */
    @Test
    public void recursive_shouldReturnListWithOneElementForSingleNode() {
        BinaryNode<Integer> root = new BinaryNode<>(1);
        List<Integer> expected = Arrays.asList(1);

        List<BinaryNode> result = traversal.getRecursive(root);

        assertEquals(expected, extractData(result));
    }

    /**
     * Tests standard pre-order traversal (Root, Left, Right).
     */
    @Test
    public void recursive_shouldReturnPreOrderTraversalForStandardTree() {
        BinaryNode<Integer> root = buildStandardTree();
        // Expected: 1, 2, 4, 5, 3
        List<Integer> expected = Arrays.asList(1, 2, 4, 5, 3);

        List<BinaryNode> result = traversal.getRecursive(root);

        assertEquals(expected, extractData(result));
    }
    
    /**
     * Tests a left-only tree to ensure the right branch logic is skipped correctly (recursive exit condition).
     */
    @Test
    public void recursive_shouldHandleLeftOnlyTree() {
        BinaryNode<Integer> root = new BinaryNode<>(1);
        root.setLeft(new BinaryNode<>(2));
        List<Integer> expected = Arrays.asList(1, 2);

        List<BinaryNode> result = traversal.getRecursive(root);

        assertEquals(expected, extractData(result));
    }

    // --- Tests for getIterative(BinaryNode) ---

    /**
     * Tests the base case: one node, ensuring the stack push/pop works once.
     */
    @Test
    public void iterative_shouldReturnListWithOneElementForSingleNode() {
        BinaryNode<Integer> root = new BinaryNode<>(1);
        List<Integer> expected = Arrays.asList(1);

        List<BinaryNode> result = traversal.getIterative(root);

        assertEquals(expected, extractData(result));
    }

    /**
     * Tests standard pre-order traversal (Root, Left, Right).
     * This hits the while loop, stack operations, and both if (hasRight) and if (hasLeft) branches.
     */
    @Test
    public void iterative_shouldReturnPreOrderTraversalForStandardTree() {
        BinaryNode<Integer> root = buildStandardTree();
        // Expected: 1, 2, 4, 5, 3
        List<Integer> expected = Arrays.asList(1, 2, 4, 5, 3);

        List<BinaryNode> result = traversal.getIterative(root);

        assertEquals(expected, extractData(result));
    }

    /**
     * Tests a right-only tree to ensure node.hasLeft() is skipped correctly.
     */
    @Test
    public void iterative_shouldHandleRightOnlyTree() {
        BinaryNode<Integer> root = new BinaryNode<>(1);
        root.setRight(new BinaryNode<>(2));
        List<Integer> expected = Arrays.asList(1, 2);

        List<BinaryNode> result = traversal.getIterative(root);

        assertEquals(expected, extractData(result));
    }
}