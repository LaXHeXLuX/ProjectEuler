package utils;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class DiophantineTest {
    @Test
    void continuedFractionTest() {
        assertArrayEquals(new int[] {1}, Diophantine.continuedFraction(1));
        assertArrayEquals(new int[] {1, 2}, Diophantine.continuedFraction(2));
        assertArrayEquals(new int[] {2, 1, 1, 1, 4}, Diophantine.continuedFraction(7));
        assertArrayEquals(new int[] {10, 1, 2, 10, 2, 1, 20}, Diophantine.continuedFraction(114));
    }
    @Test
    void pell() {
        assertArrayEquals(new long[] {}, Diophantine.pell(1));
        assertArrayEquals(new long[] {3, 2}, Diophantine.pell(2));
        assertArrayEquals(new long[] {2, 1}, Diophantine.pell(3));
        assertArrayEquals(new long[] {}, Diophantine.pell(4));
        assertArrayEquals(new long[] {9, 4}, Diophantine.pell(5));
        assertArrayEquals(new long[] {649, 180}, Diophantine.pell(13));
        assertArrayEquals(new long[] {197, 42}, Diophantine.pell(22));
        assertEquals(158070671986249L, Diophantine.pell(109)[0]);

        assertArrayEquals(new BigInteger[] {}, Diophantine.pellBig(100));
        assertEquals( new BigInteger("25052977273092427986049"), Diophantine.pellBig(409)[0]);
        assertEquals(new BigInteger("3879474045914926879468217167061449"), Diophantine.pellBig(421)[0]);
    }
    @Test
    void root() {
        assertThrows(IllegalArgumentException.class, () -> Diophantine.root(-1));
        assertEquals(0, Diophantine.root(0));
        assertEquals(1, Diophantine.root(1));
        assertEquals(-3, Diophantine.root(10));
        assertEquals(-15, Diophantine.root(255));
        assertEquals(16, Diophantine.root(256));
        assertEquals(-16, Diophantine.root(257));

        assertThrows(IllegalArgumentException.class, () -> Diophantine.root(-9_999_999_999L));
        assertEquals(0L, Diophantine.root(0L));
        assertEquals(1L, Diophantine.root(1L));
        assertEquals(1_000_000L, Diophantine.root(1_000_000_000_000L));
        assertEquals(-1_000_000L, Diophantine.root(1_000_000_000_001L));
        long big = (1L << Long.SIZE/2 - 1) + 1;
        assertEquals(big, Diophantine.root(big*big));
    }
    @Test
    void quadratic() {
        int[] empty = {};
        assertArrayEquals(empty, Diophantine.quadratic(1, 1, 1));
        assertArrayEquals(new int[] {-1}, Diophantine.quadratic(1, 2, 1));
        assertArrayEquals(empty, Diophantine.quadratic(1, 3, 1));
        assertArrayEquals(new int[] {-2}, Diophantine.quadratic(2, 5, 2));
        assertArrayEquals(new int[] {2}, Diophantine.quadratic(2, -5, 2));
        assertArrayEquals(new int[] {-1}, Diophantine.quadratic(4, 5, 1));
        assertArrayEquals(new int[] {-4, -1}, Diophantine.quadratic(1, 5, 4));
        assertArrayEquals(empty, Diophantine.quadratic(4, 4, 1));
    }
    @Test
    void digitSum() {
        assertEquals(0, Diophantine.digitSum(0));
        assertEquals(0, Diophantine.digitSum(0L));
        assertEquals(0, Diophantine.digitSum(BigInteger.ZERO));

        assertEquals(1, Diophantine.digitSum(10));
        assertEquals(1, Diophantine.digitSum(10L));
        assertEquals(1, Diophantine.digitSum(BigInteger.TEN));

        assertEquals(6, Diophantine.digitSum(123));
        assertEquals(6, Diophantine.digitSum(123L));
        assertEquals(6, Diophantine.digitSum(BigInteger.valueOf(123)));

        assertEquals(81, Diophantine.digitSum(999_999_999));
        assertEquals(162, Diophantine.digitSum(999_999_999_999_999_999L));
        assertEquals(270, Diophantine.digitSum(new BigInteger("999999999999999999999999999999")));
    }
    @Test
    void isPalindromeInBase() {
        assertTrue(Diophantine.isPalindrome(0));
        assertTrue(Diophantine.isPalindromeInBase(0, 2));
        assertTrue(Diophantine.isPalindrome(0L));
        assertTrue(Diophantine.isPalindromeInBase(0L, 2));

        assertTrue(Diophantine.isPalindromeInBase(-4, 10));
        assertFalse(Diophantine.isPalindromeInBase(-4, 2));
        assertTrue(Diophantine.isPalindromeInBase(-4L, 10));
        assertFalse(Diophantine.isPalindromeInBase(-4L, 2));

        assertTrue(Diophantine.isPalindromeInBase(1_234_554_321, 10));
        assertTrue(Diophantine.isPalindromeInBase((1<<28) + 1, 2));
        assertTrue(Diophantine.isPalindromeInBase(123_456_789_987_654_321L, 10));
        assertTrue(Diophantine.isPalindromeInBase((1L<<60) + 1, 2));

        assertFalse(Diophantine.isPalindromeInBase(123_454_320, 10));
        assertFalse(Diophantine.isPalindromeInBase((1<<30) + (1<<15), 2));
        assertFalse(Diophantine.isPalindromeInBase(123_456_789_987_654_320L, 10));
        assertFalse(Diophantine.isPalindromeInBase((1L<<60) + (1<<30), 2));
    }
    @Test
    void gcd() {
        for (int i = 1; i < 10; i++) {
            assertEquals(i, Diophantine.gcd(i, i));
        }
        assertEquals(11, Diophantine.gcd(11, 0));
        assertEquals(11, Diophantine.gcd(0, 11));
        assertEquals(1, Diophantine.gcd(3, 5));
        assertEquals(10, Diophantine.gcd(100, 10));
        assertEquals(4, Diophantine.gcd(28, 60));
    }
}