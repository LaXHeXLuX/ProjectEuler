package euler;

import utils.Divisors;

public class PE_021 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 10_000;
        boolean[] amicableNumbers = findAmicablePairs(limit);
        int sum = 0;
        for (int i = 1; i < amicableNumbers.length; i++) {
            if (amicableNumbers[i]) sum += i;
        }
        return sum;
    }

    private static int amicablePair(int n) {
        int[] divisors = Divisors.divisors(n);
        int sum = 0;
        for (int divisor : divisors) sum += divisor;
        sum -= n;
        int otherNumber = sum;
        if (otherNumber < 1) return -1;
        divisors = Divisors.divisors(otherNumber);
        sum = 0;
        for (int divisor : divisors) sum += divisor;
        sum -= otherNumber;
        return sum == n && otherNumber != n ? otherNumber : -1;
    }

    private static boolean[] findAmicablePairs(int limit) {
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
