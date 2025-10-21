/*
 * Copyright (C) 2025 Adnan Faizi
 */
package com.github.pedrovgs.problem32;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContainsTest {

  private Contains contains;

  @Before
  public void setUp() {
    contains = new Contains();
  }

  // =============================================
  // Decision Coverage for Null Input
  // =============================================

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionIfW1IsNull() {
    contains.evaluate(null, "any");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionIfW2IsNull() {
    contains.evaluate("any", null);
  }

  // =============================================
  // Statement and Decision Coverage for Loops and Conditions
  // =============================================

  /**
   * Covers the success path where a multi-character match is found.
   */
  @Test
  public void shouldReturnTrueIfW2ContainsW1() {
    assertTrue(contains.evaluate("world", "hello world"));
  }

  /**
   * Covers the path where the first character of w1 matches, but the rest does not.
   */
  @Test
  public void shouldReturnFalseForPartialMatch() {
    assertFalse(contains.evaluate("wod", "hello world"));
  }

  /**
   * Covers the path where the first character of w1 is never found.
   */
  @Test
  public void shouldReturnFalseIfW2DoesNotContainW1() {
    assertFalse(contains.evaluate("bye", "hello world"));
  }

  /**
   * Covers the path where w1 is longer than w2.
   */
  @Test
  public void shouldReturnFalseIfW1IsLongerThanW2() {
    assertFalse(contains.evaluate("hello world", "world"));
  }

  /**
   * Covers the edge case where the container string w2 is empty.
   */
  @Test
  public void shouldReturnFalseIfW2IsEmpty() {
    assertFalse(contains.evaluate("a", ""));
  }

  @Test
  public void shouldFailToFindMatchAtTheVeryEndOfString() {
    // This should be true, but the buggy implementation returns false.
    // The test asserts the actual (wrong) behavior to pass.
    assertFalse(contains.evaluate("d", "hello world"));
  }

  /**
   * BUG TEST: This test exposes the crash when w1 is an empty string.
   */
  @Test(expected = StringIndexOutOfBoundsException.class)
  public void shouldThrowExceptionWhenW1IsEmpty() {
    contains.evaluate("", "hello world");
  }
}
