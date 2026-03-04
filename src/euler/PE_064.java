package euler;

import utils.Diophantine;

public class PE_064 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 10_000;
        return String.valueOf(countOdds(limit));
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

