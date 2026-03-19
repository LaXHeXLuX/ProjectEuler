package euler;

import utils.Divisors;

public class PE_021 {
    private static int[] divisorSums;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 10_000;
        return String.valueOf(amicableNumbersSum(limit));
    }

    private static int amicableNumbersSum(int limit) {
        divisorSums = Divisors.divisorSums((int) (limit*(1 + Math.log(limit))));
        boolean[] amicableNumbers = amicablePairs(limit);

        int sum = 0;
        for (int i = 1; i < amicableNumbers.length; i++) {
            if (amicableNumbers[i]) sum += i;
        }
        return sum;
    }

    private static int amicablePair(int n) {
        int otherNumber = divisorSums[n];
        if (otherNumber < 1) return -1;
        int sum = divisorSums[otherNumber];
        if (sum == n && otherNumber != n) return otherNumber;
        return -1;
    }

    private static boolean[] amicablePairs(int limit) {
        boolean[] amicablePairs = new boolean[limit];
        for (int i = 1; i < amicablePairs.length; i++) {
            if (amicablePairs[i]) continue;
            int otherNumber = amicablePair(i);
            if (otherNumber == -1) continue;
            amicablePairs[i] = true;
            amicablePairs[otherNumber] = true;
        }
        return amicablePairs;
    }
}
