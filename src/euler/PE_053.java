package euler;

import utils.Combinations;

public class PE_053 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int lowBar = 1_000_000;
        int nLimit = 100;
        return String.valueOf(numberOfValuesGreaterThan2(lowBar, nLimit));
    }

    private static int numberOfValuesGreaterThan2(int limit, int nLimit) {
        int counter = 0;

        int n;
        for (n = 2; n <= nLimit; n+=2) {
            long value = Combinations.nChooseM(n, n >> 1);
            if (value > limit) break;
        }
        counter += (nLimit - n)/2 + 1;

        for (n = 1; n <= nLimit; n++) {
            int r;
            for (r = 1; r <= (n-1) >> 1 ; r++) {
                long value = Combinations.nChooseM(n, r);
                if (value > limit) break;
            }
            counter += 2*(((n+1) >> 1) - r);
        }

        return counter;
    }
}
