package euler;

import utils.Primes;

public class PE_069 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long limit = 1_000_000L;
        return String.valueOf(highestNumberScore(limit));
    }

    private static long highestNumberScore(long limit) {
        int[] primes = Primes.primes(100);
        long n = primes[0];
        long lastN = 0;
        int i = 1;
        while (n < limit) {
            lastN = n;
            n *= primes[i];
            if (n < 0) return lastN;
            i++;
        }
        return lastN;
    }
}
