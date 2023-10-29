import UsefulFunctions.Divisors;

import java.util.Arrays;

public class PE_021 {
    public static void main(String[] args) {
        int limit = 10_000;
        boolean[] amicableNumbers = findAmicablePairs(limit);
        int sum = 0;
        for (int i = 1; i < amicableNumbers.length; i++) {
            if (amicableNumbers[i]) sum += i;
        }
        System.out.println(sum);
    }
    private static int amicablePair(int n) {
        Divisors d = new Divisors();
        long[] divisors = d.divisors(n);
        int sum = 0;
        for (long divisor : divisors) sum += divisor;
        sum -= n;
        int otherNumber = sum;
        divisors = d.divisors(otherNumber);
        sum = 0;
        for (long divisor : divisors) sum += divisor;
        sum -= otherNumber;
        return sum == n && otherNumber != n ? otherNumber : -1;
    }
    private static boolean[] findAmicablePairs(int limit) {
        boolean[] amicablePairs = new boolean[limit];
        for (int i = 1; i < amicablePairs.length; i++) {
            if (amicablePairs[i]) continue;
            int othenNumber = (int) amicablePair(i);
            if (othenNumber == -1) continue;
            amicablePairs[i] = true;
            amicablePairs[othenNumber] = true;
        }
        return amicablePairs;
    }
}
