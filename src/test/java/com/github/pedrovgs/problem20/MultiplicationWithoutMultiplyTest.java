/*
 * Copyright (C) 2025 Adnan Fahad Faizi.
 */
package com.github.pedrovgs.problem20;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for MultiplicationWithoutMultiply class.
 * Designed to achieve full statement, edge, and conditional coverage.
 */
public class MultiplicationWithoutMultiplyTest {

  private MultiplicationWithoutMultiply multiplication;

  @Before
  public void setUp() {
    multiplication = new MultiplicationWithoutMultiply();
  }

  // =============================================
  // Conditional Coverage Tests for Sign Logic
  // =============================================

  /**
   * Covers: n1 > 0, n2 > 0
   * 'negative' condition: (false && true) || (false && true) -> false
   * 'positive': true
   * Loop 'if' condition: (false && true || true && false) -> false. Takes 'else' branch.
   */
  @Test
  public void test01() {
    assertEquals(50, multiplication.calculate(5, 10));
  }

  /**
   * Covers: n1 > 0, n2 < 0
   * 'negative' condition: (false && false) || (true && true) -> true
   * 'positive': false
   * Loop 'if' condition: (true && false || false && true) -> false. Takes 'else' branch.
   */
  @Test
  public void test02() {
    assertEquals(-50, multiplication.calculate(5, -10));
  }

  /**
   * Covers: n1 < 0, n2 > 0
   * 'negative' condition: (true && true) || (false && false) -> true
   * 'positive': false
   * Loop 'if' condition: (true && true || false && false) -> true. Takes 'if' branch.
   */
  @Test
  public void test03() {
    assertEquals(-50, multiplication.calculate(-5, 10));
  }

  /**
   * Covers: n1 < 0, n2 < 0
   * 'negative' condition: (true && false) || (true && false) -> false
   * 'positive': true
   * Loop 'if' condition: (false && false || true && true) -> true. Takes 'if' branch.
   */
  @Test
  public void test04() {
    assertEquals(50, multiplication.calculate(-5, -10));
  }

  // =============================================
  // Edge Case Coverage Tests
  // =============================================

  /**
   * Edge Case: n1 = 0.
   * This ensures the for-loop condition 'i < n1' is handled correctly
   * and the loop is skipped entirely.
   */
  @Test
  public void test05() {
    assertEquals(0, multiplication.calculate(0, 12345));
  }

  /**
   * Edge Case: n2 = 0.
   * This ensures the logic inside the loop (adding or subtracting zero) works correctly.
   */
  @Test
  public void test06() {
    assertEquals(0, multiplication.calculate(12345, 0));
  }

  /**
   * Edge Case: Both arguments are 0.
   * Combination of the two zero-argument tests.
   */
  @Test
  public void test07() {
    assertEquals(0, multiplication.calculate(0, 0));
  }

  /**
   * Edge Case: Multiplying by 1.
   * The loop should execute exactly once.
   */
  @Test
  public void test08() {
    assertEquals(99, multiplication.calculate(1, 99));
  }

  /**
   * Edge Case: Multiplying by -1.
   * The loop executes once after Math.abs(-1), and the sign logic should return the negated number.
   */
  @Test
  public void test09() {
    assertEquals(-99, multiplication.calculate(-1, 99));
  }

  /**
   * Property Test: Multiplication should be commutative (a*b = b*a).
   * This helps verify the logic is sound regardless of which number is larger or used for the loop counter.
   */
  @Test
  public void test10() {
    int result1 = multiplication.calculate(7, 8);
    int result2 = multiplication.calculate(8, 7);
    assertEquals(result1, result2);
  }

  /**
   * Edge Case: Integer Overflow.
   * This test documents the known limitation of the method. The multiplication of
   * 60000 * 60000 is 3,600,000,000, which exceeds Integer.MAX_VALUE (2,147,483,647).
   * The result will wrap around, which is standard integer behavior in Java.
   * The expected value is the result of the overflow.
   */
  @Test
  public void test21() {
    // 3,600,000,000 in a 32-bit signed int overflows to -694,967,296
    int expectedOverflowResult = -694967296;
    assertEquals(expectedOverflowResult, multiplication.calculate(60000, 60000));
  }
}
