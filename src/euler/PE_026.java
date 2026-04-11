package euler;

import utils.Diophantine;

public class PE_026 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long limit = 1000;
        return String.valueOf(longestReciprocalCycleUnder(limit));
    }

    private static long longestReciprocalCycleUnder(long limit) {
        long longestCycle = 0;
        long longestNumber = 0;
        for (long i = limit-1; i > 0; i--) {
            if (i % 2 == 0 || i % 5 == 0) continue;
            if (i < longestCycle) return longestNumber;
            long reciprocalCycleLength = cycleLength(i);
            if (reciprocalCycleLength > longestCycle) {
                longestCycle = reciprocalCycleLength;
                longestNumber = i;
            }
        }
        return longestNumber;
    }

    private static long cycleLength(long n) {
        while (n % 2 == 0) n /= 2;
        while (n % 5 == 0) n /= 5;
        if (n == 1) return 0;
        return Diophantine.ord(10, n);
    }
}
