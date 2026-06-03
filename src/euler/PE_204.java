package euler;

import utils.Primes;

public class PE_204 {
    private static int[] primes;

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
        return hamming(primes.length-1, limit) + 1;
    }

    private static long hamming(int i, long L) {
        if (i == 0) return 63 - Long.numberOfLeadingZeros(L);
        if (L <= Integer.MAX_VALUE) return hamming(i, (int) L);

        return hamming(i-1, L) + hamming(i, L/primes[i]) + 1;
    }

    private static long hamming(int i, int L) {
        if (i == 0) return 31 - Integer.numberOfLeadingZeros(L);
        if (L < primes[i]) return hamming(i-1, L);

        return hamming(i-1, L) + hamming(i, L/primes[i]) + 1;
    }
}
