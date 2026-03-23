package euler;

import utils.Primes;

public class PE_050 {
    private static boolean[] composites;
    private static int[] primes;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 1_000_000;

        composites = Primes.compositeSieve(limit);
        primes = Primes.primes(composites);

        int result = sumOfConsecutivePrimesSummingToPrime2();
        return String.valueOf(result);
    }

    private static int sumOfConsecutivePrimesSummingToPrime2() {
        long[] sums = new long[primes.length + 1];
        for (int i = 0; i < primes.length; i++) {
            sums[i + 1] = sums[i] + primes[i];
        }

        int bestStart = 0;
        int bestLength = 0;
        int limit = primes[primes.length - 1];

        for (int i = 0; i < primes.length; i++) {
            if (sums[i] + bestLength + 1 > limit) break;

            for (int end = i + bestLength + 1; end <= primes.length; end++) {
                long sum = sums[end] - sums[i];
                if (sum > limit) break;
                if (!composites[(int) sum >> 1]) {
                    bestStart = i;
                    bestLength = end - i;
                }
            }
        }

        return (int) (sums[bestStart + bestLength] - sums[bestStart]);
    }
}
