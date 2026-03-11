package euler;

import utils.Primes;

public class PE_357 {
    private static boolean[] composites;

    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        int limit = 100_000_000;
        double s = System.currentTimeMillis();
        composites = Primes.compositeSieve(limit+1);
        double e = System.currentTimeMillis();
        System.out.println("composites " + (e-s) + " ms");
        return String.valueOf(sumOfAll(limit));
    }

    private static long sumOfAll(int limit) {
        long sum = 3;
        int b = 60;
        int[] arr = {10, 18, 22, 30, 42, 58};

        for (int i = 6; i < b; i+=4) {
            if (i > limit) break;
            if (primeGenerating(i)) sum += i;
        }

        int iLimit = limit/b;
        for (int i = 1; i <= iLimit; i++) {
            int base = i*b;
            for (int j : arr) {
                int n = base + j;
                if (n > limit) break;
                if (primeGenerating(n)) sum += n;
            }
        }

        return sum;
    }

    private static boolean primeGenerating(int n) {
        if (composites[(n+1)/2] || composites[(n/2+2)/2]) return false;
        int d;
        for (d = 3; d*d <= n; d++) {
            if (n % d != 0) continue;
            if (composites[(d + n/d)/2]) break;
        }
        return d*d > n;
    }
}
