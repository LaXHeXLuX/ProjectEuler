package utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PrimesTest {

    @Test
    void compositeSieve() {
        boolean[] primesTo30 = {true, false, false, false, true, false, false, true, false, false, true, false, true, true, false};
        assertArrayEquals(primesTo30, Primes.compositeSieve(30));
        boolean[] manyPrimes = Primes.compositeSieve(100_000);
        assertEquals(100_000/2, manyPrimes.length);
        assertTrue(manyPrimes[77 >> 1]);
        assertFalse(manyPrimes[101 >> 1]);
        assertTrue(manyPrimes[1001 >> 1]);
        assertTrue(manyPrimes[10001 >> 1]);
        assertFalse(manyPrimes[33331 >> 1]);
        assertTrue(manyPrimes[34567 >> 1]);
        assertTrue(manyPrimes[99999 >> 1]);
    }
    @Test
    void primes() {
        assertArrayEquals(new int[] {}, Primes.primes(1));
        assertArrayEquals(new int[] {2}, Primes.primes(2));
        assertArrayEquals(new int[] {2}, Primes.primes(Primes.compositeSieve(1)));
        int[] primes10 = {2, 3, 5, 7};
        assertArrayEquals(primes10, Primes.primes(10));
        int[] primes30 = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        assertArrayEquals(primes30, Primes.primes(30));
        int[] primes100 = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        assertArrayEquals(primes100, Primes.primes(100));
        assertArrayEquals(primes100, Primes.primes(Primes.compositeSieve(100)));
        int[] primes210 = Primes.primes(210);
        assertEquals(-(primes210.length+1), Arrays.binarySearch(primes210, 209));
        assertArrayEquals(primes210, Primes.primes(Primes.compositeSieve(210)));
        int[] primes1500 = Primes.primes(1500);
        assertEquals(239, primes1500.length);
        assertEquals(99, Arrays.binarySearch(primes1500, 541));
        assertEquals(-100, Arrays.binarySearch(primes1500, 540));
        assertArrayEquals(primes1500, Primes.primes(Primes.compositeSieve(1500)));
    }
    @Test
    void primeFactors() {
        assertNotEquals(new Primes.PF(1), new Object());
        assertNotEquals(new Primes.PF(2, 1), new Primes.PF(2, 2));
        assertNotEquals(new Primes.PF(2, 1), new Primes.PF(3, 1));

        Primes.PF[] pf1 = {};
        assertArrayEquals(pf1, Primes.primeFactors(1));
        Primes.PF[] pf2 = {new Primes.PF(2)};
        assertArrayEquals(pf2, Primes.primeFactors(2));
        Primes.PF[] pf7 = {new Primes.PF(7)};
        assertArrayEquals(pf7, Primes.primeFactors(7));
        Primes.PF[] pf12 = {new Primes.PF(2, 2), new Primes.PF(3)};
        assertArrayEquals(pf12, Primes.primeFactors(12));
        Primes.PF[] pf69 = {new Primes.PF(3), new Primes.PF(23)};
        assertArrayEquals(pf69, Primes.primeFactors(69));
        Primes.PF[] pf77 = {new Primes.PF(7), new Primes.PF(11)};
        assertArrayEquals(pf77, Primes.primeFactors(77));
        Primes.PF[] pf256 = {new Primes.PF(2, 8)};
        assertArrayEquals(pf256, Primes.primeFactors(256));
        Primes.PF[] pf10000 = {new Primes.PF(2, 5), new Primes.PF(5, 5)};
        assertArrayEquals(pf10000, Primes.primeFactors(100000));
        Primes.PF[] pfBig = {new Primes.PF(5, 1), new Primes.PF(349, 2), new Primes.PF(2441, 2)};
        assertEquals("[349 ^ 2]", pfBig[1].toString());
        assertArrayEquals(pfBig, Primes.primeFactors(	3_628_744_721_405L));
    }
    @Test
    void isPrime() {
        assertFalse(Primes.isPrime(1));
        assertTrue(Primes.isPrime(2));
        assertTrue(Primes.isPrime(3));
        assertFalse(Primes.isPrime(9));
        assertFalse(Primes.isPrime(77));
        assertTrue(Primes.isPrime(79));
        assertFalse(Primes.isPrime(120));
        assertFalse(Primes.isPrime(121));
        assertFalse(Primes.isPrime(61*61));
        assertFalse(Primes.isPrime(101*101));
        assertFalse(Primes.isPrime(103*103));

        assertTrue(Primes.isPrime(289586599663L));
        assertTrue(Primes.isPrime(217203134209L));
        assertFalse(Primes.isPrime(217203134207L));
        assertFalse(Primes.isPrime(217203134208L));
    }
    @Test
    void nthPrime() {
        assertEquals(2, Primes.nthPrime(1));
        assertEquals(541, Primes.nthPrime(100));
        assertEquals(4127, Primes.nthPrime(567));
        assertEquals(104729, Primes.nthPrime(10_000));
        assertEquals(-1, Primes.nthPrime(-1));
    }
    @Test
    void eulersTotient() {
        assertEquals(0, Primes.eulersTotient(1));
        assertEquals(1, Primes.eulersTotient(2));
        assertEquals(2, Primes.eulersTotient(3));
        assertEquals(2, Primes.eulersTotient(3));

        assertEquals(6, Primes.eulersTotient(9));
        assertEquals(10, Primes.eulersTotient(11));

        assertEquals(79180, Primes.eulersTotient(87109));
    }
}