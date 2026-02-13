package euler;

import utils.Primes;

public class PE_187 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 100_000_000;
        return twoPrimeFactors(limit);
    }

    private static int twoPrimeFactors(int limit) {
        int count = 0;

        int[] primes = Primes.primes(limit/2);
        for (int i = 0; i < primes.length; i++) {
            for (int j = i; j < primes.length; j++) {
                long p = (long) primes[i] * primes[j];
                if (p > limit) break;
                count++;
            }
        }

        return count;
    }
}
