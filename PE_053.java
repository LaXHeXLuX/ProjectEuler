import UsefulFunctions.Combinations;

import java.math.BigInteger;

public class PE_053 {
    public static void main(String[] args) {
        int lowBar = 1_000_000;
        int nLimit = 100;
        System.out.println(numberOfValuesGreaterThan(lowBar, nLimit));
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
