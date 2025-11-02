package utils;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {
    private <T> void assertFractionEquals(T num, T den, Fraction<T> fraction) {
        assertEquals(num, fraction.num);
        assertEquals(den, fraction.den);
    }
    private <T> void assertSimplifiedFractionEquals(T num, T den, Fraction<T> fraction) {
        assertFractionEquals(num, den, fraction.simplify());
    }
    private <T> void assertDeepArrayEquals(T[] arr1, T[] arr2) {
        assertTrue(Arrays.deepEquals(arr1, arr2));
    }
    @Test
    void toStringTest() {
        assertEquals("{1, 1}", new Fraction<>(1, 1).toString());
        assertEquals("{3, 5}", new Fraction<>(3, 5).toString());
        assertEquals("{3, 51234567898765432123456789}", new Fraction<>(BigInteger.valueOf(3), new BigInteger("51234567898765432123456789")).toString());
    }
    @Test
    void createFraction() {
        assertThrows(IllegalArgumentException.class, () -> new Fraction<>(0, 0));
        assertFractionEquals(0, 1, new Fraction<>(0, 1));
        assertFractionEquals(-1, 1, new Fraction<>(1, -1));
    }
    @Test
    void simplifyFraction() {
        Fraction<Integer> fraction = new Fraction<>(1, 1);
        assertFractionEquals(1, 1, fraction.simplify());

        fraction = new Fraction<>(2, 2);
        assertFractionEquals(1, 1, fraction.simplify());

        fraction = new Fraction<>(26, 8);
        assertFractionEquals(13, 4, fraction.simplify());
    }
    @Test
    void addFraction() {
        Fraction<Integer> fraction1 = new Fraction<>(1, 1);
        Fraction<Integer> fraction2 = new Fraction<>(0, 1);
        Fraction<Integer> sum = fraction1.add(fraction2);
        assertSimplifiedFractionEquals(1, 1, sum);

        fraction1 = new Fraction<>(1, 1);
        fraction2 = new Fraction<>(1, 1);
        sum = fraction1.add(fraction2);
        assertSimplifiedFractionEquals(2, 1, sum);

        fraction1 = new Fraction<>(1, 2);
        fraction2 = new Fraction<>(1, 3);
        sum = fraction1.add(fraction2);
        assertSimplifiedFractionEquals(5, 6, sum);
    }
    @Test
    void subtractFraction() {
        Fraction<Integer> fraction1 = new Fraction<>(1, 1);
        Fraction<Integer> fraction2 = new Fraction<>(0, 1);
        Fraction<Integer> difference = fraction1.subtract(fraction2);
        assertSimplifiedFractionEquals(1, 1, difference);

        fraction1 = new Fraction<>(0, 1);
        fraction2 = new Fraction<>(1, 1);
        difference = fraction1.subtract(fraction2);
        assertSimplifiedFractionEquals(-1, 1, difference);

        fraction1 = new Fraction<>(1, 2);
        fraction2 = new Fraction<>(1, 3);
        difference = fraction1.subtract(fraction2);
        assertSimplifiedFractionEquals(1, 6, difference);
    }
    @Test
    void multiplyFraction() {
        Fraction<Integer> fraction1 = new Fraction<>(1, 1);
        Fraction<Integer> fraction2 = new Fraction<>(0, 1);
        Fraction<Integer> product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(0, 1, product);

        fraction1 = new Fraction<>(0, 1);
        fraction2 = new Fraction<>(1, 1);
        product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(0, 1, product);

        fraction1 = new Fraction<>(1, 3);
        fraction2 = new Fraction<>(3, 1);
        product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(1, 1, product);

        fraction1 = new Fraction<>(1, 2);
        fraction2 = new Fraction<>(1, 3);
        product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(1, 6, product);
    }
    @Test
    void divideFraction() {
        Fraction<Integer> dividend = new Fraction<>(1, 1);
        Fraction<Integer> divider = new Fraction<>(0, 1);
        assertThrows(IllegalArgumentException.class, () -> dividend.divide(divider));

        Fraction<Integer> fraction1 = new Fraction<>(1, 3);
        Fraction<Integer> fraction2 = new Fraction<>(3, 1);
        Fraction<Integer> quotient = fraction1.divide(fraction2);
        assertSimplifiedFractionEquals(1, 9, quotient);

        fraction1 = new Fraction<>(1, 2);
        fraction2 = new Fraction<>(1, 3);
        quotient = fraction1.divide(fraction2);
        assertSimplifiedFractionEquals(3, 2, quotient);
    }
    @Test
    void getCycle() {
        Fraction<Integer> fraction = new Fraction<>(-1, 1);
        assertThrows(RuntimeException.class, fraction::getCycle);

        fraction = new Fraction<>(1, 1);
        assertArrayEquals(new int[][] {}, fraction.getCycle());

        fraction = new Fraction<>(3, 2);
        assertDeepArrayEquals(new int[][] {{5}, {}}, fraction.getCycle());

        fraction = new Fraction<>(1, 3);
        assertDeepArrayEquals(new int[][] {{}, {3}}, fraction.getCycle());

        fraction = new Fraction<>(100, 1001);
        assertDeepArrayEquals(new int[][] {{}, {0, 9, 9, 9, 0, 0}}, fraction.getCycle());

        Fraction<BigInteger> bigFraction = new Fraction<>(BigInteger.valueOf(-1), BigInteger.TWO);
        assertThrows(RuntimeException.class, bigFraction::getCycle);

        bigFraction = new Fraction<>(BigInteger.valueOf(3), BigInteger.ONE);
        assertDeepArrayEquals(new int[][] {}, bigFraction.getCycle());

        bigFraction = new Fraction<>(BigInteger.valueOf(3), BigInteger.TWO);
        assertDeepArrayEquals(new int[][] {{5}, {}}, bigFraction.getCycle());

        bigFraction = new Fraction<>(BigInteger.valueOf(1), BigInteger.valueOf(3));
        assertDeepArrayEquals(new int[][] {{}, {3}}, bigFraction.getCycle());

        bigFraction = new Fraction<>(BigInteger.valueOf(100), BigInteger.valueOf(1001));
        assertDeepArrayEquals(new int[][] {{}, {0, 9, 9, 9, 0, 0}}, bigFraction.getCycle());
    }
    @Test
    void createBigFraction() {
        assertThrows(IllegalArgumentException.class, () -> new Fraction<>(BigInteger.ZERO, BigInteger.ZERO));
        assertFractionEquals(BigInteger.ZERO, BigInteger.ONE, new Fraction<>(BigInteger.ZERO, BigInteger.ONE));
        assertFractionEquals(BigInteger.valueOf(-1), BigInteger.ONE, new Fraction<>(BigInteger.ONE, BigInteger.valueOf(-1)));
    }
    @Test
    void simplifyBigFraction() {
        Fraction<BigInteger> fraction = new Fraction<>(BigInteger.ONE, BigInteger.ONE);
        assertFractionEquals(BigInteger.ONE, BigInteger.ONE, fraction.simplify());

        fraction = new Fraction<>(BigInteger.TWO, BigInteger.TWO);
        assertFractionEquals(BigInteger.ONE, BigInteger.ONE, fraction.simplify());

        fraction = new Fraction<>(BigInteger.valueOf(26L), BigInteger.valueOf(8L));
        assertFractionEquals(BigInteger.valueOf(13), BigInteger.valueOf(4), fraction.simplify());
    }
    @Test
    void addBigFraction() {
        Fraction<BigInteger> fraction1 = new Fraction<>(BigInteger.ONE, BigInteger.ONE);
        Fraction<BigInteger> fraction2 = new Fraction<>(BigInteger.ZERO, BigInteger.ONE);
        Fraction<BigInteger> sum = fraction1.add(fraction2);
        assertSimplifiedFractionEquals(BigInteger.ONE, BigInteger.ONE, sum);

        fraction1 = new Fraction<>(BigInteger.ONE, BigInteger.ONE);
        fraction2 = new Fraction<>(BigInteger.ONE, BigInteger.ONE);
        sum = fraction1.add(fraction2);
        assertSimplifiedFractionEquals(BigInteger.TWO, BigInteger.ONE, sum);

        fraction1 = new Fraction<>(BigInteger.ONE, BigInteger.TWO);
        fraction2 = new Fraction<>(BigInteger.ONE, BigInteger.valueOf(3));
        sum = fraction1.add(fraction2);
        assertSimplifiedFractionEquals(BigInteger.valueOf(5), BigInteger.valueOf(6), sum);
    }
    @Test
    void subtractBigFraction() {
        Fraction<BigInteger> fraction1 = new Fraction<>(BigInteger.ONE, BigInteger.ONE);
        Fraction<BigInteger> fraction2 = new Fraction<>(BigInteger.ZERO, BigInteger.ONE);
        Fraction<BigInteger> difference = fraction1.subtract(fraction2);
        assertSimplifiedFractionEquals(BigInteger.ONE, BigInteger.ONE, difference);

        fraction1 = new Fraction<>(BigInteger.ZERO, BigInteger.ONE);
        fraction2 = new Fraction<>(BigInteger.ONE, BigInteger.ONE);
        difference = fraction1.subtract(fraction2);
        assertSimplifiedFractionEquals(BigInteger.valueOf(-1), BigInteger.ONE, difference);

        fraction1 = new Fraction<>(BigInteger.ONE, BigInteger.TWO);
        fraction2 = new Fraction<>(BigInteger.ONE, BigInteger.valueOf(3));
        difference = fraction1.subtract(fraction2);
        assertSimplifiedFractionEquals(BigInteger.ONE, BigInteger.valueOf(6), difference);
    }
    @Test
    void multiplyBigFraction() {
        Fraction<BigInteger> fraction1 = new Fraction<>(BigInteger.ONE, BigInteger.ONE);
        Fraction<BigInteger> fraction2 = new Fraction<>(BigInteger.ZERO, BigInteger.ONE);
        Fraction<BigInteger> product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(BigInteger.ZERO, BigInteger.ONE, product);

        fraction1 = new Fraction<>(BigInteger.ZERO, BigInteger.ONE);
        fraction2 = new Fraction<>(BigInteger.ONE, BigInteger.ONE);
        product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(BigInteger.ZERO, BigInteger.ONE, product);

        fraction1 = new Fraction<>(BigInteger.ONE, BigInteger.valueOf(3));
        fraction2 = new Fraction<>(BigInteger.valueOf(3), BigInteger.ONE);
        product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(BigInteger.ONE, BigInteger.ONE, product);

        fraction1 = new Fraction<>(BigInteger.ONE, BigInteger.TWO);
        fraction2 = new Fraction<>(BigInteger.ONE, BigInteger.valueOf(3));
        product = fraction1.multiply(fraction2);
        assertSimplifiedFractionEquals(BigInteger.ONE, BigInteger.valueOf(6), product);
    }
    @Test
    void divideBigFraction() {
        Fraction<BigInteger> dividend = new Fraction<>(BigInteger.ONE, BigInteger.ONE);
        Fraction<BigInteger> divider = new Fraction<>(BigInteger.ZERO, BigInteger.ONE);
        assertThrows(IllegalArgumentException.class, () -> dividend.divide(divider));

        Fraction<BigInteger> fraction1 = new Fraction<>(BigInteger.ONE, BigInteger.valueOf(3));
        Fraction<BigInteger> fraction2 = new Fraction<>(BigInteger.valueOf(3), BigInteger.ONE);
        Fraction<BigInteger> quotient = fraction1.divide(fraction2);
        assertSimplifiedFractionEquals(BigInteger.ONE, BigInteger.valueOf(9), quotient);

        fraction1 = new Fraction<>(BigInteger.ONE, BigInteger.TWO);
        fraction2 = new Fraction<>(BigInteger.ONE, BigInteger.valueOf(3));
        quotient = fraction1.divide(fraction2);
        assertSimplifiedFractionEquals(BigInteger.valueOf(3), BigInteger.TWO, quotient);
    }
    @Test
    void compareTo() {
        Fraction<Integer> lf1 = new Fraction<>(0, 1);
        Fraction<Integer> lf2 = new Fraction<>(1, 1);
        assertTrue(lf1.compareTo(lf2) < 0);
        lf2 = new Fraction<>(0, 2);
        assertEquals(0, lf1.compareTo(lf2));

        Fraction<BigInteger> bf1 = new Fraction<>(BigInteger.ZERO, BigInteger.ONE);
        Fraction<BigInteger> bf2 = new Fraction<>(BigInteger.ONE, BigInteger.ONE);
        assertTrue(bf1.compareTo(bf2) < 0);
        bf2 = new Fraction<>(BigInteger.ZERO, BigInteger.TWO);
        assertEquals(0, bf1.compareTo(bf2));
    }
}