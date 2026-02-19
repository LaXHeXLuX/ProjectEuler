package euler;

import utils.Combinations;
import utils.Diophantine;
import utils.Fraction;

import java.math.BigInteger;

public class PE_323 {

    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int bits = 32;
        Fraction<BigInteger> P = P(bits);
        int digits = 10;
        return Math.round(P.doubleValue() * Diophantine.pow(10, digits));
    }

    private static Fraction<BigInteger> P(int n) {
        Fraction<BigInteger> sum = new Fraction<>(BigInteger.ZERO);
        for (int i = 1; i <= n; i++) {
            BigInteger c = Combinations.nChooseMBigInteger(n, i);
            BigInteger powerOfTwo = BigInteger.TWO.pow(i);
            Fraction<BigInteger> fraction = new Fraction<>(powerOfTwo, powerOfTwo.subtract(BigInteger.ONE));
            fraction = fraction.multiply(c);
            if (i % 2 == 0) fraction = fraction.neg();
            sum = sum.add(fraction);
        }
        return sum;
    }
}
