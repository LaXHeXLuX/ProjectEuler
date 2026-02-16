package euler;

import utils.Primes;

public class PE_058 {

    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int[] fraction = {1, 10};
        return firstSpiralLengthWithPrimeDiagonalRatioBelow(fraction);
    }

    private static int firstSpiralLengthWithPrimeDiagonalRatioBelow(int[] fraction) {
        int e1 = fraction[0];
        int d1 = fraction[1];
        int length = 3;
        int primeCounter = 3;
        while (e1 * 2*length < primeCounter * d1) {
            length += 2;
            long[] corners = primeCornerNumbers(length);
            for (long corner : corners) {
                if (Primes.isPrime(corner)) {
                    primeCounter++;
                }
            }
        }
        return length;
    }

    private static long[] primeCornerNumbers(int length) {
        long lastCorner = (long) (length - 2) * (length - 2);
        long[] corners = new long[3];
        for (int i = 0; i < 3; i++) {
            corners[i] = lastCorner + (long) (length - 1) * (i+1);
        }
        return corners;
    }
}
