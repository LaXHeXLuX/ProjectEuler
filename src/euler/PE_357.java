package euler;

import utils.Primes;

public class PE_357 {
    private static boolean[] composites;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 100_000_000;
        composites = Primes.compositeSieve(limit+1);
        return String.valueOf(sumOfAll(limit));
    }

    private static long sumOfAll(int limit) {
        long sum = 1+2+6;

        for (int i = 10; i < limit; i+=4) {
            if (composites[i/2] || composites[i/4 + 1]) continue;
            if (primeGenerating(i)) sum += i;
        }

        return sum;
    }

    private static boolean primeGenerating(int n) {
        for (int d = 3; d*d <= n; d++) {
            if (n % d == 0 && composites[(d + n/d)/2]) return false;
        }
        return true;
    }
}
