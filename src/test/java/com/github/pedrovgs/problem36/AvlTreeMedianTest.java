package com.github.pedrovgs.problem36;

import com.github.pedrovgs.binarytree.BinaryNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class AvlTreeMedianTest {

  private static final double DELTA = 0.1;

  private AvlTreeMedian avlTreeMedian;

  @Before public void setUp() {
    avlTreeMedian = new AvlTreeMedian();
  }

  // --- EXISTING TESTS ---
  @Test(expected = IllegalArgumentException.class) public void shouldNotAcceptNullTrees() {
    avlTreeMedian.find(null);
  }

  @Test public void shouldReturnRootElementIfTheTreeContainsJustOneElement() {
    BinaryNode<Integer> root = new BinaryNode<Integer>(1);

    double median = avlTreeMedian.find(root);

    assertEquals(1, median, DELTA);
  }

  @Test public void shouldReturnRootElementIfTheTreeContainsTFiveElements() {
    BinaryNode<Integer> root = new BinaryNode<Integer>(2);
    BinaryNode<Integer> n1 = new BinaryNode<Integer>(1);
    BinaryNode<Integer> n3 = new BinaryNode<Integer>(3);
    BinaryNode<Integer> n4 = new BinaryNode<Integer>(4);
    BinaryNode<Integer> n5 = new BinaryNode<Integer>(-1);

    root.setLeft(n1);
    root.setRight(n3);
    n3.setRight(n4);
    n1.setLeft(n5);

    double median = avlTreeMedian.find(root);

    assertEquals(2, median, DELTA); // Sorted list: -1, 1, 2, 3, 4. Median is 2 (index 2).
  }
  // ------------------- END EXISTING TESTS -------------------

  // ------------------- NEW TESTS FOR COVERAGE INCREASE -------------------

  /**
   * Targets the critical missing branch: if (sortedElements.size() % 2 == 0).
   * Tree: 1 < 2. Sorted: [1, 2]. Size = 2. Median = (1+2)/2 = 1.5
   * Branch: true branch of size % 2 == 0.
   */
  @Test public void shouldReturnAverageOfTwoMiddleElementsForTwoNodeTree() {
    BinaryNode<Integer> root = new BinaryNode<Integer>(2);
    BinaryNode<Integer> n1 = new BinaryNode<Integer>(1);
    root.setLeft(n1);

    double median = avlTreeMedian.find(root);

    assertEquals(1.5, median, DELTA);
  }

  /**
   * Targets the critical missing branch: if (sortedElements.size() % 2 == 0).
   * Tree: 1 < 2 < 3 < 4. Sorted: [1, 2, 3, 4]. Size = 4. Median = (2+3)/2 = 2.5
   * Branch: true branch of size % 2 == 0, with more complex list indexing.
   */
  @Test public void shouldReturnAverageOfTwoMiddleElementsForFourNodeTree() {
    BinaryNode<Integer> root = new BinaryNode<Integer>(3);
    BinaryNode<Integer> n1 = new BinaryNode<Integer>(1);
    BinaryNode<Integer> n2 = new BinaryNode<Integer>(2);
    BinaryNode<Integer> n4 = new BinaryNode<Integer>(4);

    root.setLeft(n2);
    root.setRight(n4);
    n2.setLeft(n1);

    double median = avlTreeMedian.find(root);

    assertEquals(2.5, median, DELTA);
  }

  /**
   * Tests a larger odd-sized tree to ensure correct indexing (size=7).
   * Sorted: [1, 2, 3, 4, 5, 6, 7]. Median is 4 (index 3).
   * Branch: false branch of size % 2 == 0.
   */
  @Test public void shouldReturnMiddleElementForSevenNodeTree() {
    // This tree is not an AVL, but the method uses an in-order traversal, so the structure is irrelevant.
    BinaryNode<Integer> root = new BinaryNode<Integer>(4);
    root.setLeft(new BinaryNode<Integer>(2));
    root.getLeft().setLeft(new BinaryNode<Integer>(1));
    root.getLeft().setRight(new BinaryNode<Integer>(3));
    root.setRight(new BinaryNode<Integer>(6));
    root.getRight().setLeft(new BinaryNode<Integer>(5));
    root.getRight().setRight(new BinaryNode<Integer>(7));
    
    double median = avlTreeMedian.find(root);

    assertEquals(4, median, DELTA);
  }
}