package euler;

import java.math.BigInteger;

public class PE_057 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 1000;
        return String.valueOf(countExceedsUnder(limit));
    }

    private static int countExceedsUnder(int limit) {
        int counter = 0;

        BigInteger[] fraction = {BigInteger.valueOf(3), BigInteger.TWO};
        BigInteger powTen = BigInteger.TEN;
        for (int i = 1; i < limit; i++) {
            if (fraction[0].compareTo(powTen) >= 0) {
                if (fraction[1].compareTo(powTen) < 0) counter++;
                powTen = powTen.multiply(BigInteger.TEN);
            }
            fraction = nextIterationOfSquareRootOfTwo(fraction);
        }

        return counter;
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
