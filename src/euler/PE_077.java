package euler;

import utils.Primes;

public class PE_077 {
    private static int[] primes;
    private static long[][] waysToSum;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 5_000;
        return String.valueOf(firstToSumInOverNWays(n));
    }

    private static int firstToSumInOverNWays(int n) {
        int primeLimit = 1;
        int result = firstToSumInOverNWays(n, primeLimit);
        while (result == -1) {
            primeLimit *= 2;
            result = firstToSumInOverNWays(n, primeLimit);
        }
        return result;
    }

    private static int firstToSumInOverNWays(int n, int limit) {
        primes = Primes.primes(limit);
        waysToSum = new long[limit+1][limit+1];
        int number = 1;
        long answer = waysToSumWithPrimes(1);
        while (answer <= n && number < limit) {
            number++;
            answer = waysToSumWithPrimes(number);
        }
        if (number >= limit) return -1;
        return number;
    }

    private static long waysToSumWithPrimes(int n) {
        return waysToSumWithPrimes(n, 0);
    }

    private static long waysToSumWithPrimes(int n, int biggestAdderIndex) {
        if (n == 0) return 1;
        if (n == 1) return 0;
        if (waysToSum[n][biggestAdderIndex] > 0) return waysToSum[n][biggestAdderIndex];

        long sum = 0;
        for (int i = biggestAdderIndex; i < primes.length && primes[i] <= n; i++) {
            sum += waysToSumWithPrimes(n-primes[i], i);
        }

        waysToSum[n][biggestAdderIndex] = sum;
        return sum;
    }
}
