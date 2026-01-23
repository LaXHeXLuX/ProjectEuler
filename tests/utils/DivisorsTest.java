package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisorsTest {

    @Test
    void divisors() {
        assertThrows(RuntimeException.class, () -> Divisors.divisors(0));
        assertArrayEquals(new int[] {1}, Divisors.divisors(1));
        assertArrayEquals(new int[] {1, 2, 5, 10}, Divisors.divisors(10));
        assertArrayEquals(new int[] {1, 2, 4, 8, 16, 32}, Divisors.divisors(32));
        assertArrayEquals(new int[] {1, 2, 4, 5, 10, 20, 25, 50, 100}, Divisors.divisors(100));
        assertArrayEquals(new int[] {1, 101}, Divisors.divisors(101));
        assertArrayEquals(new int[] {1, 2, 4, 5, 8, 10, 20, 25, 40, 50, 100, 125, 200, 250, 500, 1000}, Divisors.divisors(1000));

        assertThrows(RuntimeException.class, () -> Divisors.divisors(0L));
        assertArrayEquals(new long[] {1}, Divisors.divisors(1L));
        assertArrayEquals(new long[] {1, 2, 4, 5, 10, 20, 25, 50, 100}, Divisors.divisors(100L));
        assertArrayEquals(new long[] {1, 11, 121, 1331, 14641, 161051, 1771561, 19487171, 214358881, 2357947691L}, Divisors.divisors(11L*11*11*11*11*11*11*11*11));
    }
    @Test
    void sumOfDivisors() {
        assertEquals(1, Divisors.sumOfDivisors(2));
        assertEquals(28, Divisors.sumOfDivisors(28));
        assertEquals(1, Divisors.sumOfDivisors(101));
        assertEquals(810, Divisors.sumOfDivisors(360));
    }
}