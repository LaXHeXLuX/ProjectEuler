package euler;

import utils.Primes;

public class PE_010 {
    private static int[] primes;
    private static long[][] memo;

    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        int L = 2_000_000;
        return String.valueOf(primeSum(L));
    }

    private static long primeSum(long L) {
        int sqrt = (int) Math.sqrt(L);
        primes = Primes.primes(sqrt);
        int a = primes.length;
        memo = new long[sqrt][a];

        long smallPrimeSum = 0;
        for (int p : primes) smallPrimeSum += p;

        return sum(L, a) + smallPrimeSum - 1;
    }

    private static long sum(long L, int a) {
        if (L < memo.length) return sum((int) L, a);
        if (a == 0) return L*(L +1)/2;

        return sum(L, a-1) - primes[a-1] * sum(L/primes[a-1], a-1);
    }

    private static long sum(int L, int a) {
        if (L == 0) return 0;
        if (a == 0) return L*(L +1L)/2;

        if (memo[L][a] == 0) memo[L][a] = sum(L, a-1) - primes[a-1] * sum(L/primes[a-1], a-1);
        return memo[L][a];
    }
}
