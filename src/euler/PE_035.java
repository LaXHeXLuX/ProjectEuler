package euler;

import utils.Diophantine;
import utils.Primes;

import java.util.HashSet;
import java.util.Set;

public class PE_035 {
    private static Set<Long> cyclicPrimes;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long limit = 1_000_000;
        cyclicPrimesUnder(limit);
        return String.valueOf(cyclicPrimes.size());
    }

    private static boolean cyclicPrime(long n, int d) {
        long p10 = Diophantine.pow10[d-1];
        for (int i = 0; i < d; i++) {
            n = (n % 10)*p10 + n/10;
            if (!Primes.isPrime(n)) return false;
        }
        return true;
    }

    private static void cyclicPrimesUnder(long limit) {
        cyclicPrimes = new HashSet<>(Set.of(2L, 3L, 5L, 7L));
        for (int i = 1; i < 10; i+=2) {
            if (i == 5) continue;
            cyclicPrimesUnder(limit, i, 1, i);
        }
    }

    private static void cyclicPrimesUnder(long limit, long current, int d, int min) {
        if (current*10 > limit) return;

        d++;
        for (int i = min; i < 10; i+=2) {
            if (i == 5) continue;
            long n = 10*current + i;
            cyclicPrimesUnder(limit, n, d, min);
            if (cyclicPrime(n, d)) addCyclics(n, d);
        }
    }

    private static void addCyclics(long n, int d) {
        long p10 = Diophantine.pow10[d-1];
        for (int i = 0; i < d; i++) {
            n = (n % 10)*p10 + n/10;
            cyclicPrimes.add(n);
        }
    }
}
