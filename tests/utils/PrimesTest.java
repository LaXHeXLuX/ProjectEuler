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
    void findPrimeFactors() {
        assertArrayEquals(new long[] {}, Primes.findPrimeFactors(1));
        assertArrayEquals(new long[] {2}, Primes.findPrimeFactors(2));
        assertArrayEquals(new long[] {7}, Primes.findPrimeFactors(7));
        assertArrayEquals(new long[] {2, 2, 3}, Primes.findPrimeFactors(12));
        assertArrayEquals(new long[] {3, 23}, Primes.findPrimeFactors(69));
        assertArrayEquals(new long[] {7, 11}, Primes.findPrimeFactors(77));
        assertArrayEquals(new long[] {2, 2, 2, 2, 2, 2, 2, 2}, Primes.findPrimeFactors(256));
        assertArrayEquals(new long[] {2, 2, 2, 2, 2, 5, 5, 5, 5, 5}, Primes.findPrimeFactors(100000));
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