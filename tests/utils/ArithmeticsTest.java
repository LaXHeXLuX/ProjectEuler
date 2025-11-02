package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

public class ArithmeticsTest {

    @Test
    void add() {
        assertEquals(3, Arithmetics.INT.add(1, 2));
        assertEquals(333_333_333_333_333L, Arithmetics.LONG.add(111_111_111_111_111L, 222_222_222_222_222L));
        assertEquals(0.99999999, Arithmetics.DOUBLE.add(0.12345678, 0.87654321), 1e-12);
        assertEquals(
                new BigInteger("3330333033303330333033303330333"),
                Arithmetics.BIG.add(
                        new BigInteger("1110111011101110111011101110111"),
                        new BigInteger("2220222022202220222022202220222")
                )
        );
    }
    @Test
    void multiply() {
        assertEquals(6, Arithmetics.INT.mul(2, 3));
        assertEquals(999_999_999_999_999L, Arithmetics.LONG.mul(111_111_111_111_111L, 9L));
        assertEquals(0.09, Arithmetics.DOUBLE.mul(0.45, 0.2), 1e-12);
        assertEquals(
                new BigInteger("3330333033303330333033303330333"),
                Arithmetics.BIG.mul(
                        new BigInteger("1110111011101110111011101110111"),
                        new BigInteger("3")
                )
        );
    }
    @Test
    void subtract() {
        assertEquals(1, Arithmetics.INT.sub(3, 2));
        assertEquals(111_111_111_111_111L, Arithmetics.LONG.sub(333_333_333_333_333L, 222_222_222_222_222L));
        assertEquals(0.12345678, Arithmetics.DOUBLE.sub(0.99999999, 0.87654321), 1e-12);
        assertEquals(
                new BigInteger("1110111011101110111011101110111"),
                Arithmetics.BIG.sub(
                        new BigInteger("3330333033303330333033303330333"),
                        new BigInteger("2220222022202220222022202220222")
                )
        );
    }
    @Test
    void divide() {
        assertEquals(2, Arithmetics.INT.div(6, 3));
        assertEquals(111_111_111_111_111L, Arithmetics.LONG.div(999_999_999_999_999L, 9L));
        assertEquals(0.45, Arithmetics.DOUBLE.div(0.09, 0.2), 1e-12);
        assertEquals(
                new BigInteger("1110111011101110111011101110111"),
                Arithmetics.BIG.div(
                        new BigInteger("3330333033303330333033303330333"),
                        new BigInteger("3")
                )
        );
    }
    @Test
    void remainder() {
        assertEquals(1, Arithmetics.INT.rem(7, 3));
        assertEquals(8L, Arithmetics.LONG.rem(999_999_999_999_998L, 9L));
        assertEquals(0.15, Arithmetics.DOUBLE.rem(0.9, 0.25), 1e-12);
        assertEquals(
                new BigInteger("2"),
                Arithmetics.BIG.rem(
                        new BigInteger("3330333033303330333033303330332"),
                        new BigInteger("3")
                )
        );
    }
    @Test
    void gcd() {
        assertEquals(5, Arithmetics.INT.gcd(85, 100));
        assertEquals(9L, Arithmetics.LONG.gcd(999_999_999_999_999L, 9L));
        assertThrows(UnsupportedOperationException.class, () -> Arithmetics.DOUBLE.gcd(1.0, 1.0));
        assertEquals(
                new BigInteger("3"),
                Arithmetics.BIG.gcd(
                        new BigInteger("3330333033303330333033303330333"),
                        new BigInteger("3")
                )
        );
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
    }
}
