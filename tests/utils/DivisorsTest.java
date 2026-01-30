package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisorsTest {

    @Test
    void divisors() {
        assertThrows(IllegalArgumentException.class, () -> Divisors.divisors(0));
        assertArrayEquals(new int[] {1}, Divisors.divisors(1));
        assertArrayEquals(new int[] {1, 2, 5, 10}, Divisors.divisors(10));
        assertArrayEquals(new int[] {1, 2, 4, 8, 16, 32}, Divisors.divisors(32));
        assertArrayEquals(new int[] {1, 2, 4, 5, 10, 20, 25, 50, 100}, Divisors.divisors(100));
        assertArrayEquals(new int[] {1, 101}, Divisors.divisors(101));
        assertArrayEquals(new int[] {1, 2, 4, 5, 8, 10, 20, 25, 40, 50, 100, 125, 200, 250, 500, 1000}, Divisors.divisors(1000));

        assertThrows(IllegalArgumentException.class, () -> Divisors.divisors(0L));
        assertArrayEquals(new long[] {1}, Divisors.divisors(1L));
        assertArrayEquals(new long[] {1, 2, 4, 5, 10, 20, 25, 50, 100}, Divisors.divisors(100L));
        assertArrayEquals(new long[] {1, 11, 121, 1331, 14641, 161051, 1771561, 19487171, 214358881, 2357947691L}, Divisors.divisors(11L*11*11*11*11*11*11*11*11));
        assertArrayEquals(new long[] {1, 3, 9, 27, 37, 81, 111, 333, 999, 1369, 2997, 4107, 12321, 36963, 110889, 333667, 1001001, 3003003, 9009009, 12345679, 27027027, 37037037, 111111111, 333333333, 456790123, 999999999, 1370370369, 4111111107L, 12333333321L, 36999999963L, 111333666889L, 334001000667L, 1002003002001L, 3006009006003L, 4119345674893L, 9018027018009L, 12358037024679L, 37074111074037L, 111222333222111L, 152415789971041L, 333666999666333L, 457247369913123L, 1371742109739369L, 4115226329218107L, 12345678987654321L}, Divisors.divisors(12345678987654321L));

        assertThrows(IllegalArgumentException.class, () -> Divisors.divisors(2, -1));
        assertArrayEquals(new long[] {1}, Divisors.divisors(1, 1));
        assertArrayEquals(new long[] {1}, Divisors.divisors(2, 0));
        assertArrayEquals(new long[] {1, 2, 4, 5, 10, 20, 25, 50, 100}, Divisors.divisors(10, 2));
        assertArrayEquals(new long[] {1, 2, 4, 8, 16, 32}, Divisors.divisors(2, 5));

    }
    @Test
    void sumOfDivisors() {
        assertEquals(1, Divisors.sumOfDivisors(2));
        assertEquals(28, Divisors.sumOfDivisors(28));
        assertEquals(55, Divisors.sumOfDivisors(36));
        assertEquals(1, Divisors.sumOfDivisors(101));
        assertEquals(810, Divisors.sumOfDivisors(360));
    }
}