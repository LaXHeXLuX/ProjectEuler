package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

public class ArithmeticsTest {
    private static final BigInteger big1 = new BigInteger("1110111011101110111011101110111");
    private static final BigInteger big2 = new BigInteger("2220222022202220222022202220222");
    private static final BigInteger big3 = new BigInteger("3330333033303330333033303330333");

    @Test
    void add() {
        assertEquals(3, Arithmetics.INT.add(1, 2));
        assertEquals(333_333_333_333_333L, Arithmetics.LONG.add(111_111_111_111_111L, 222_222_222_222_222L));
        assertEquals(0.99999999, Arithmetics.DOUBLE.add(0.12345678, 0.87654321), 1e-12);
        assertEquals(big3, Arithmetics.BIG.add(big1, big2));
    }
    @Test
    void multiply() {
        assertEquals(6, Arithmetics.INT.mul(2, 3));
        assertEquals(999_999_999_999_999L, Arithmetics.LONG.mul(111_111_111_111_111L, 9L));
        assertEquals(0.09, Arithmetics.DOUBLE.mul(0.45, 0.2), 1e-12);
        assertEquals(big3, Arithmetics.BIG.mul(big1, new BigInteger("3")));
    }
    @Test
    void negate() {
        assertEquals(-1, Arithmetics.INT.neg(1));
        assertEquals(-333_333_333_333_333L, Arithmetics.LONG.neg(333_333_333_333_333L));
        assertEquals(-0.12345678, Arithmetics.DOUBLE.neg(0.12345678), 1e-12);
        assertEquals(big1.multiply(new BigInteger("-1")), Arithmetics.BIG.neg(big1));
    }
    @Test
    void subtract() {
        assertEquals(1, Arithmetics.INT.sub(3, 2));
        assertEquals(111_111_111_111_111L, Arithmetics.LONG.sub(333_333_333_333_333L, 222_222_222_222_222L));
        assertEquals(0.12345678, Arithmetics.DOUBLE.sub(0.99999999, 0.87654321), 1e-12);
        assertEquals(big1, Arithmetics.BIG.sub(big3, big2));
    }
    @Test
    void divide() {
        assertEquals(2, Arithmetics.INT.div(6, 3));
        assertEquals(111_111_111_111_111L, Arithmetics.LONG.div(999_999_999_999_999L, 9L));
        assertEquals(0.45, Arithmetics.DOUBLE.div(0.09, 0.2), 1e-12);
        assertEquals(big1, Arithmetics.BIG.div(big2, BigInteger.TWO));
    }
    @Test
    void remainder() {
        assertEquals(1, Arithmetics.INT.rem(7, 3));
        assertEquals(8L, Arithmetics.LONG.rem(999_999_999_999_998L, 9L));
        assertEquals(0.15, Arithmetics.DOUBLE.rem(0.9, 0.25), 1e-12);
        assertEquals(BigInteger.ONE, Arithmetics.BIG.rem(big3, BigInteger.TWO));
    }
    @Test
    void gcd() {
        assertEquals(5, Arithmetics.INT.gcd(85, 100));
        assertEquals(9L, Arithmetics.LONG.gcd(999_999_999_999_999L, 9L));
        assertThrows(UnsupportedOperationException.class, () -> Arithmetics.DOUBLE.gcd(1.0, 1.0));
        assertEquals(BigInteger.TWO, Arithmetics.BIG.gcd(big2, BigInteger.TWO));
    }
    @Test
    void constants() {
        assertEquals(0, Arithmetics.INT.zero());
        assertEquals(0L, Arithmetics.LONG.zero());
        assertEquals(0.0, Arithmetics.DOUBLE.zero());
        assertEquals(BigInteger.ZERO, Arithmetics.BIG.zero());

        assertEquals(1, Arithmetics.INT.one());
        assertEquals(1L, Arithmetics.LONG.one());
        assertEquals(1.0, Arithmetics.DOUBLE.one());
        assertEquals(BigInteger.ONE, Arithmetics.BIG.one());

        assertEquals(10, Arithmetics.INT.ten());
        assertEquals(10L, Arithmetics.LONG.ten());
        assertEquals(10.0, Arithmetics.DOUBLE.ten());
        assertEquals(BigInteger.TEN, Arithmetics.BIG.ten());
    }
    @Test
    void valueOf() {
        assertEquals(5, Arithmetics.INT.valueOf(5));
        assertThrows(IllegalArgumentException.class, () -> Arithmetics.INT.valueOf(111_111_111_111L));
        assertEquals(6L, Arithmetics.LONG.valueOf(6));
        assertEquals(7.0, Arithmetics.DOUBLE.valueOf(7));
        assertEquals(BigInteger.valueOf(8), Arithmetics.BIG.valueOf(8));

        assertEquals(-7, Arithmetics.INT.valueOf("-7"));
        assertEquals(-111_111_111_111L, Arithmetics.LONG.valueOf("-111111111111"));
        assertEquals(0.9, Arithmetics.DOUBLE.valueOf("0.9"));
        assertEquals(BigInteger.valueOf(4), Arithmetics.BIG.valueOf("4"));
    }
    @Test
    void intValue() {
        assertEquals(5, Arithmetics.INT.intValue(5));
        assertEquals(6, Arithmetics.LONG.intValue(6L));
        assertEquals(7, Arithmetics.DOUBLE.intValue(7.0));
        assertEquals(10, Arithmetics.BIG.intValue(BigInteger.TEN));
    }
    @Test
    void compare() {
        assertEquals(-1, Arithmetics.INT.compare(0, 1));
        assertEquals(0, Arithmetics.INT.compare(1, 1));
        assertEquals(1, Arithmetics.INT.compare(1, 0));

        assertEquals(-1, Arithmetics.LONG.compare(0L, 1L));
        assertEquals(0, Arithmetics.LONG.compare(1L, 1L));
        assertEquals(1, Arithmetics.LONG.compare(1L, 0L));

        assertEquals(-1, Arithmetics.DOUBLE.compare(0.0, 1.0));
        assertEquals(0, Arithmetics.DOUBLE.compare(1.0, 1.0));
        assertEquals(1, Arithmetics.DOUBLE.compare(1.0, 0.0));

        assertEquals(-1, Arithmetics.BIG.compare(BigInteger.ZERO, java.math.BigInteger.ONE));
        assertEquals(0, Arithmetics.BIG.compare(BigInteger.ONE, BigInteger.ONE));
        assertEquals(1, Arithmetics.BIG.compare(BigInteger.ONE, BigInteger.ZERO));
    }
    @Test
    void of() {
        assertEquals(Arithmetics.INT, Arithmetics.of(Integer.class));
        assertThrows(IllegalArgumentException.class, () -> Arithmetics.of(String.class));
    }
}
