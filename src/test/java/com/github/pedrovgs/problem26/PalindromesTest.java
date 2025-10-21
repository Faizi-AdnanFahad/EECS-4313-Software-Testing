/*
 * Copyright (C) 2025 Adnan Fahad Faizi
 */
package com.github.pedrovgs.problem26;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the Palindromes class.
 * Designed to achieve full statement, edge, and conditional coverage.
 */
public class PalindromesTest {

  private Palindromes palindromes;

  @Before
  public void setUp() {
    palindromes = new Palindromes();
  }

  // =============================================
  // Edge Case Coverage
  // =============================================

  /**
   * NUllpointer checker
   */
  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentExceptionForNullInput() {
    palindromes.evaluate(null);
  }

  /**
   * Edge Case: An empty string.
   */
  @Test
  public void shouldReturnTrueForEmptyString() {
    assertTrue(palindromes.evaluate(""));
  }

  /**
   * Edge Case: A single character string.
   */
  @Test
  public void shouldReturnTrueForSingleCharacterString() {
    assertTrue(palindromes.evaluate("a"));
  }

  // =============================================
  // Statement and Conditional Coverage
  // =============================================

  /**
   * Covers a standard true case with an odd number of characters.
   */
  @Test
  public void shouldReturnTrueForOddLengthPalindrome() {
    assertTrue(palindromes.evaluate("racecar"));
  }

  /**
   * Covers a standard true case with an even number of characters.
   */
  @Test
  public void shouldReturnTrueForEvenLengthPalindrome() {
    assertTrue(palindromes.evaluate("noon"));
  }

  /**
   * Covers a standard false case.
   */
  @Test
  public void shouldReturnFalseForNonPalindrome() {
    assertFalse(palindromes.evaluate("hello"));
  }

  /**
   * Covers a false case where the mismatch is not at the ends.
   */
  @Test
  public void shouldReturnFalseForLongerNonPalindrome() {
    assertFalse(palindromes.evaluate("bananab"));
  }

  /**
   * Confirms that the current implementation is case-sensitive.
   */
  @Test
  public void shouldReturnFalseForCaseSensitivePalindrome() {
    assertFalse(palindromes.evaluate("Level"));
  }

  /**
   * Confirms that the current implementation treats spaces as significant characters.
   */
  @Test
  public void shouldReturnFalseForPalindromeWithSpaces() {
    assertFalse(palindromes.evaluate("taco cat"));
  }
  
  /**
   * Confirms that numeric characters are handled correctly.
   */
  @Test
  public void shouldReturnTrueForNumericPalindrome() {
    assertTrue(palindromes.evaluate("12321"));
  }
}
