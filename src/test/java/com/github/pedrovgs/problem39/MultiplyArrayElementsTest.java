package com.github.pedrovgs.problem39;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class MultiplyArrayElementsTest {

  private MultiplyArrayElements multiplyArrayElements;

  @Before public void setUp() {
    multiplyArrayElements = new MultiplyArrayElements();
  }

  // --- EXISTING TEST (Included for completeness) ---

  @Test(expected = IllegalArgumentException.class) public void shouldNotAcceptNullArrays() {
    multiplyArrayElements.multiply(null);
  }

  // ------------------- NEW TESTS FOR COVERAGE INCREASE -------------------

  /**
   * Targets the defensive check: if (input.length == 0).
   * This hits a critical, currently missed branch.
   */
  @Test public void shouldReturnEmptyArrayIfInputArrayIsEmpty() {
    int[] input = {};
    int[] expected = {};
    assertArrayEquals(expected, multiplyArrayElements.multiply(input));
  }

  /**
   * Tests the minimal array case where the loops run only once or twice.
   * Input: [2, 3]. Output: [3, 2].
   */
  @Test public void shouldMultiplyArrayWithTwoElements() {
    int[] input = {2, 3};
    int[] expected = {3, 2};
    assertArrayEquals(expected, multiplyArrayElements.multiply(input));
  }

  /**
   * Tests a standard case to ensure all three for loops run multiple times.
   * Input: [1, 2, 3].
   * Front: [1, 1, 2]
   * Rear: [6, 3, 1]
   * Output: [6, 3, 2]
   */
  @Test public void shouldMultiplyArrayWithThreeElements() {
    int[] input = {1, 2, 3};
    int[] expected = {6, 3, 2};
    assertArrayEquals(expected, multiplyArrayElements.multiply(input));
  }
  
  /**
   * Tests a case with negative numbers and zero (which the algorithm can handle).
   * Input: [2, 0, -3].
   * Front: [1, 2, 0]
   * Rear: [0, -3, 1]
   * Output: [0, -6, 0]
   */
  @Test public void shouldHandleZeroAndNegativeNumbers() {
    int[] input = {2, 0, -3, 1};
    // Expected: [0, -6, 0, 0]
    // Element 0: (0 * -3 * 1) = 0
    // Element 1: (2 * -3 * 1) = -6
    // Element 2: (2 * 0 * 1) = 0
    // Element 3: (2 * 0 * -3) = 0
    int[] expected = {0, -6, 0, 0};
    assertArrayEquals(expected, multiplyArrayElements.multiply(input));
  }
}