package com.github.pedrovgs.problem50;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class UniqueCharsTest {

  private UniqueChars uniqueChars;

  @Before public void setUp() {
    uniqueChars = new UniqueChars();
  }

  // --- EXISTING TESTS (Included for completeness) ---

  @Test(expected = IllegalArgumentException.class) public void shouldNotAcceptNullStringsAsInput() {
    uniqueChars.evaluate(null); // This test also covers validateInput() for evaluate2() if called
  }

  @Test public void shouldReturnTrueIfInputStringIsEmpty() {
    // Corrected assertion name, empty string has unique chars.
    assertTrue(uniqueChars.evaluate(""));
  }

  // ------------------- NEW TESTS FOR COVERAGE INCREASE -------------------

  // --- Tests for evaluate() (HashSet-based) ---

  /**
   * Targets the successful exit branch: return true.
   * Tests unique characters to ensure the 'if (charsCounter.contains...)' block is missed.
   */
  @Test public void evaluate_shouldReturnTrueForUniqueString() {
    assertTrue(uniqueChars.evaluate("abcde"));
  }

  /**
   * Targets the failure branch: return false.
   * This is the critical missed instruction/branch coverage path.
   */
  @Test public void evaluate_shouldReturnFalseForNonUniqueString() {
    assertFalse(uniqueChars.evaluate("hello")); // 'l' is repeated.
  }
  
  /**
   * Targets a longer unique string to ensure the loop runs many times.
   */
  @Test public void evaluate_shouldReturnTrueForLongUniqueString() {
    assertTrue(uniqueChars.evaluate("abcdefghijk"));
  }

  // --- Tests for evaluate2() (Array-based, space optimization) ---

  /**
   * Targets the validateInput() check for evaluate2.
   */
  @Test(expected = IllegalArgumentException.class) public void evaluate2_shouldNotAcceptNullStringsAsInput() {
    uniqueChars.evaluate2(null);
  }

  /**
   * Targets the successful exit branch: return true.
   * Tests unique characters to ensure the 'if (chars[c] >= 1)' branch is missed.
   */
  @Test public void evaluate2_shouldReturnTrueForUniqueString() {
    assertTrue(uniqueChars.evaluate2("xyz123"));
  }

  /**
   * Targets the failure branch: return false.
   * This is the critical missed instruction/branch coverage path for evaluate2.
   */
  @Test public void evaluate2_shouldReturnFalseForNonUniqueString() {
    assertFalse(uniqueChars.evaluate2("java")); // 'a' is repeated.
  }
  
  /**
   * Targets the empty string edge case for evaluate2.
   */
  @Test public void evaluate2_shouldReturnTrueForEmptyString() {
    assertTrue(uniqueChars.evaluate2(""));
  }
}