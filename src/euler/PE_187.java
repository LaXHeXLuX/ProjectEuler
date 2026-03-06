package euler;

import utils.Primes;

import java.util.Arrays;

public class PE_187 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 100_000_000;
        return String.valueOf(twoPrimeFactors(limit));
    }

    private static int twoPrimeFactors(int limit) {
        int count = 0;

        int[] primes = Primes.primes(limit/2);
        int end = Arrays.binarySearch(primes, (int) Math.sqrt(limit));
        if (end < 0) end = -end-1;
        for (int i = 0; i < end; i++) {
            int index = Arrays.binarySearch(primes, limit/primes[i])+1;
            if (index < 0) index = -index;
            index -= i;
            count += index;
        }

        return count;
    }
}
