package com.github.pedrovgs.problem41;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests for GoThroughMatrixInSpiral class.
 */
public class GoThroughMatrixInSpiralTest {

  private GoThroughMatrixInSpiral goThroughMatrixInSpiral;

  @Before public void setUp() {
    goThroughMatrixInSpiral = new GoThroughMatrixInSpiral();
  }

  // --- EXISTING TESTS (Modified for correctness) ---

  @Test(expected = IllegalArgumentException.class)
  public void shouldNotAcceptNullInstancesAsInput() {
    goThroughMatrixInSpiral.go(null);
  }

  // NOTE: The original test assertion was incorrect. The result should be an empty array.
  @Test public void shouldReturnEmptyArrayIfMatrixIsEmpty() {
    int[][] matrix = { }; // Matrix has 0 rows
    int[] result = goThroughMatrixInSpiral.go(matrix);
    
    assertArrayEquals(new int[0], result); 
  }

  // ------------------- NEW TESTS FOR COVERAGE INCREASE -------------------

  /**
   * Targets the special case: if (m == 1) inside the while loop.
   * This is for a matrix with only one row (e.g., 1x3).
   */
  @Test public void shouldHandleSingleRowMatrix() {
    int[][] matrix = {{1, 2, 3}};
    int[] expected = {1, 2, 3};
    assertArrayEquals(expected, goThroughMatrixInSpiral.go(matrix));
  }
  
  /**
   * Targets the special case: else if (n == 1) inside the while loop.
   * This is for a matrix with only one column (e.g., 3x1).
   */
  @Test public void shouldHandleSingleColumnMatrix() {
    int[][] matrix = {{1}, {2}, {3}};
    int[] expected = {1, 2, 3};
    assertArrayEquals(expected, goThroughMatrixInSpiral.go(matrix));
  }

  /**
   * Tests the core logic: a 2x2 matrix, minimal spiral.
   * This forces the four nested for-loops (top, right, bottom, left) to execute once.
   */
  @Test public void shouldGoThrough2x2MatrixInSpiral() {
    int[][] matrix = {
        {1, 2},
        {4, 3}
    };
    int[] expected = {1, 2, 3, 4};
    assertArrayEquals(expected, goThroughMatrixInSpiral.go(matrix));
  }

  /**
   * Tests a standard even-sized matrix (3x4), ensuring the while loop terminates correctly.
   */
  @Test public void shouldGoThrough3x4MatrixInSpiral() {
    int[][] matrix = {
        {1, 2, 3, 4},
        {10, 11, 12, 5},
        {9, 8, 7, 6}
    };
    int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    assertArrayEquals(expected, goThroughMatrixInSpiral.go(matrix));
  }
  
  /**
   * Tests a standard odd-sized square matrix (3x3).
   * This forces the outer spiral (4 loops) AND hits the m==1 or n==1 special case in the final iteration.
   */
  @Test public void shouldGoThrough3x3MatrixInSpiralHittingCenter() {
    int[][] matrix = {
        {1, 2, 3},
        {8, 9, 4},
        {7, 6, 5}
    };
    int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    assertArrayEquals(expected, goThroughMatrixInSpiral.go(matrix));
  }
}