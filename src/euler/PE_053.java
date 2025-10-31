package src.euler;

import util.Combinations;

import java.math.BigInteger;

public class PE_053 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int lowBar = 1_000_000;
        int nLimit = 100;
        return numberOfValuesGreaterThan(lowBar, nLimit);
    }

    private static int numberOfValuesGreaterThan(int lowBar, int nLimit) {
        int counter = 0;

        for (int n = 1; n <= nLimit; n++) {
            for (int r = 1; r <= n ; r++) {
                BigInteger value = Combinations.nChooseMBigInteger(n, r);
                if (value.compareTo(BigInteger.valueOf(lowBar)) > 0) counter++;
            }
        }

        return counter;
    }
}
