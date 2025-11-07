package euler;

import java.math.BigInteger;

public class PE_057 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1000;
        return countExceedsUnder(limit);
    }

    private static int countExceedsUnder(int limit) {
        int counter = 0;

        BigInteger[] fraction = {BigInteger.valueOf(3), BigInteger.TWO};
        for (int i = 1; i < limit; i++) {
            if (numeratorExceedsDenominator(fraction)) counter++;
            fraction = nextIterationOfSquareRootOfTwo(fraction);
        }

        return counter;
    }

    private static boolean numeratorExceedsDenominator(BigInteger[] fraction) {
        BigInteger n = fraction[0];
        BigInteger d = fraction[1];
        while (n.compareTo(BigInteger.TEN) >= 0) {
            if (d.compareTo(BigInteger.TEN) < 0) return true;
            n = n.divide(BigInteger.TEN);
            d = d.divide(BigInteger.TEN);
        }
        return false;
    }

    private static BigInteger[] nextIterationOfSquareRootOfTwo(BigInteger[] previousIteration) {
        previousIteration[0] = previousIteration[0].subtract(previousIteration[1]);
        BigInteger[] nextIteration = new BigInteger[2];
        nextIteration[1] = previousIteration[0].add(previousIteration[1].multiply(BigInteger.TWO));
        nextIteration[0] = previousIteration[1];
        nextIteration[0] = nextIteration[0].add(nextIteration[1]);
        return nextIteration;
    }
}
