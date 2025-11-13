package euler;

import utils.Diophantine;

public class PE_064 {
    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int limit = 10_000;
        return countOdds(limit);
    }

    private static int countOdds(int limit) {
        int counter = 0;

        for (int i = 2; i <= limit; i++) {
            int[] continuedFraction = Diophantine.continuedFraction(i);
            if (continuedFraction.length < 2) continue;
            if (continuedFraction.length % 2 == 0) counter++;
        }

        return counter;
    }
}

