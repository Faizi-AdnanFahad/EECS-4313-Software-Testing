package com.github.pedrovgs.problem43;

import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for CombinationOfIntegers class.
 */
public class CombinationOfIntegersTest {

  private CombinationOfIntegers combinationOfIntegers;

  @Before public void setUp() {
    combinationOfIntegers = new CombinationOfIntegers();
  }

  // --- EXISTING TEST (Included for completeness) ---

  @Test(expected = IllegalArgumentException.class)
  public void shouldNotAcceptNullInstancesAsInput() {
    combinationOfIntegers.calculate(null);
  }
  


  // --- Helper method from original code (for assertion clarity) ---

  private void assertSetContainsSet(Set<Set<Integer>> result, int... elements) {
    Set<Integer> expected = new HashSet<Integer>();
    for (int i : elements) {
      expected.add(i);
    }
    assertTrue("Result set must contain the combination: " + expected, result.contains(expected));
  }


  // ------------------- NEW TESTS FOR COVERAGE INCREASE -------------------

  /**
   * Targets the base case: if (originalSet.isEmpty()).
   * This is the recursive exit condition.
   */
  @Test public void shouldReturnSetContainingOnlyEmptySetForEmptyInput() {
    Set<Integer> input = new HashSet<>();
    Set<Set<Integer>> result = combinationOfIntegers.calculate(input);

    // Expected result: {{}} (One element, the empty set)
    assertEquals(1, result.size());
    assertTrue(result.contains(new HashSet<Integer>()));
  }

  /**
   * Targets the minimum working recursion: a single element set.
   * Input: {1}. Expected: {{}, {1}}. Size = 2.
   */
  @Test public void shouldReturnTwoCombinationsForOneElementSet() {
    Set<Integer> input = new HashSet<>();
    input.add(1);
    Set<Set<Integer>> result = combinationOfIntegers.calculate(input);

    // Tests the recursive call, the loop, and the two sets added (newSet and set).
    assertEquals(2, result.size());
    assertSetContainsSet(result);      // {}
    assertSetContainsSet(result, 1);   // {1}
  }

  /**
   * Tests the core recursive logic loop for two elements.
   * Input: {1, 2}. Expected: {{}, {1}, {2}, {1, 2}}. Size = 4.
   */
  @Test public void shouldReturnFourCombinationsForTwoElementSet() {
    Set<Integer> input = new HashSet<>();
    input.add(1);
    input.add(2);
    Set<Set<Integer>> result = combinationOfIntegers.calculate(input);

    // This forces the 'for (Set<Integer> set : calculate(rest))' loop to run multiple times.
    assertSetContainsSet(result);
    assertSetContainsSet(result, 1);
    assertSetContainsSet(result, 2);
    assertSetContainsSet(result, 1, 2);
  }

  /**
   * Tests a three-element set (more complex recursion).
   * Input: {1, 2, 3}. Expected: 2^3 = 8 combinations.
   */
  @Test public void shouldReturnEightCombinationsForThreeElementSet() {
    Set<Integer> input = new HashSet<>();
    input.add(1);
    input.add(2);
    input.add(3);
    Set<Set<Integer>> result = combinationOfIntegers.calculate(input);

    assertEquals(8, result.size());
  }
}