package euler;

import utils.Primes;

public class PE_204 {
    private static int[] primes;
    private static long[][] memo;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int type = 100;
        long limit = 1_000_000_000L;
        return String.valueOf(hammingNumbers(type, limit));
    }

    private static long hammingNumbers(int type, long limit) {
        primes = Primes.primes(type+1);
        int memoSize = (int) Math.pow(limit, 0.25);
        if (memoSize < primes[primes.length-1]) memoSize = primes[primes.length-1];
        memo = new long[primes.length][memoSize];
        return hamming(primes.length-1, limit) + 1;
    }

    private static long hamming(int index, long limit) {
        if (index == 0) return 63 - Long.numberOfLeadingZeros(limit);
        if (limit < memo[index].length) return hamming(index, (int) limit);

        return hamming(index-1, limit) + hamming(index, limit/primes[index]) + 1;
    }

    private static long hamming(int i, int L) {
        if (i == 0) return 31 - Integer.numberOfLeadingZeros(L);
        if (L < primes[i]) return hamming(i-1, L);

        if (memo[i][L] != 0) return memo[i][L];
        long result = hamming(i-1, L) + hamming(i, L/primes[i]) + 1;
        memo[i][L] = result;
        return result;
    }
}
