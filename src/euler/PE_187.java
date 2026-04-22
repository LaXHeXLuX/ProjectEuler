package euler;

import utils.Primes;

import java.util.Arrays;

public class PE_187 {
    private static int[] primes;
    private static long[][] memo;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long limit = 100_000_000L;
        return String.valueOf(twoPrimeFactors(limit));
    }

    private static long twoPrimeFactors(long limit) {
        long count = 0;

        int sqrt = (int) Math.sqrt(limit);
        primes = Primes.primes(sqrt);
        memo = new long[sqrt][primes.length];

        for (int i = primes.length-1; i >= 0; i--) {
            count += primeCount(limit/primes[i]) - i;
        }

        return count;
    }

    private static long primeCount(long n) {
        int sqrt = (int) Math.sqrt(n);
        int a = Arrays.binarySearch(primes, sqrt) + 1;
        if (a < 0) a = -a;
        return count(n, a) + a - 1;
    }

    private static long count(long n, int a) {
        if (n < memo.length && a < memo[(int) n].length) return count((int) n, a);
        if (a == 0) return n;

        return count(n, a-1) - count(n/primes[a-1], a-1);
    }

    private static long count(int n, int a) {
        if (n == 0) return 0;
        if (a == 0) return n;

        if (memo[n][a] == 0) memo[n][a] = count(n, a-1) - count(n/primes[a-1], a-1);
        return memo[n][a];
    }
}
