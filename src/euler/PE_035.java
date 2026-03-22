package euler;

import utils.Diophantine;
import utils.Primes;

import java.util.ArrayList;
import java.util.List;

public class PE_035 {
    private static boolean[] composites;
    private static final List<Integer> cyclicPrimes = new ArrayList<>();

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 1_000_000;
        composites = Primes.compositeSieve(limit);
        cyclicPrimesUnder(limit);
        System.out.println(cyclicPrimes);
        return String.valueOf(cyclicPrimes.size());
    }

    private static boolean cyclicPrime(int n) {
        int digits = (int) (Math.log10(n) + 1);
        for (int i = 1; i < digits; i++) {
            int powTen = (int) Diophantine.pow10[i];
            int rem = n % powTen;
            int cyclicNumber = n / powTen + (int) Diophantine.pow10[digits-i] * rem;
            if (cyclicNumber % 2 == 0 || composites[cyclicNumber >> 1]) return false;
        }
        return true;
    }

    private static void cyclicPrimesUnder(int limit) {
        cyclicPrimes.addAll(List.of(2, 3, 5, 7));
        for (int i = 1; i < 10; i+=2) {
            cyclicPrimesUnder(limit, i);
        }
    }

    private static void cyclicPrimesUnder(int limit, int current) {
        if (current*10 > limit) return;

        for (int i = 1; i < 10; i+=2) {
            int n = 10*current + i;
            cyclicPrimesUnder(limit, n);
            if (!composites[n >> 1] && cyclicPrime(n)) cyclicPrimes.add(n);
        }
    }
}
