package euler;

import utils.Primes;

public class PE_069 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        long limit = 1_000_000L;
        return highestNumberScore(limit);
    }

    private static long highestNumberScore(long limit) {
        int primeLimit = (int) Math.sqrt(limit);
        if (primeLimit > 100) primeLimit = 100;
        int[] primes = Primes.primes(primeLimit);
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
