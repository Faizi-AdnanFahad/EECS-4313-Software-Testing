/*
 * Copyright (C) 2025 Adnan Faizi
 */
package com.github.pedrovgs.problem27;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReverseSentenceTest {

  private ReverseSentence reverseSentence;

  @Before
  public void setUp() {
    reverseSentence = new ReverseSentence();
  }

  /**
   * Covers the 'if (sentence == null)' decision, expecting an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentExceptionForNullInput() {
    reverseSentence.reverse(null);
  }

  /**
   * Covers the main execution path:
   * - The 'for' loop executes multiple times.
   * - The 'if (i != 0)' decision is true for the first N-1 iterations.
   * - The 'if (i != 0)' decision is false for the final iteration.
   */
  @Test
  public void shouldReverseStandardSentence() {
    String input = "Pedro Vicente Gómez";
    String expected = "Gómez Vicente Pedro";
    assertEquals(expected, reverseSentence.reverse(input));
  }

  /**
   * Covers the edge case where the input is an empty string.
   */
  @Test
  public void shouldReturnEmptyStringForEmptyInput() {
    assertEquals("", reverseSentence.reverse(""));
  }

  /**
   * Covers the edge case of a sentence with only one word.
   */
  @Test
  public void shouldReturnSameWordForSingleWordSentence() {
    assertEquals("Pedro", reverseSentence.reverse("Pedro"));
  }

  /**
   * Documents and tests the behavior of split() with multiple spaces.
   */
  @Test
  public void shouldHandleMultipleSpacesBetweenWords() {
    String input = "a  b   c";
    String expected = "c   b  a";
    assertEquals(expected, reverseSentence.reverse(input));
  }

  /**
   * Documents and tests the behavior of split() with leading and trailing spaces.
   */
  @Test
  public void shouldHandleLeadingAndTrailingSpaces() {
    String input = " leading and trailing ";
    String expected = "trailing and leading ";
    assertEquals(expected, reverseSentence.reverse(input));
  }
}
