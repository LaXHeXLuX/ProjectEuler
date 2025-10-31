package src.euler;

import util.Converter;
import util.Primes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PE_077 {
    private static int[] primes;
    private static Map<String, Long> waysToSumWithPrimes;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        waysToSumWithPrimes = new HashMap<>();
        int primeLimit = 10_000_000;
        primes = getPrimesBelow(primeLimit);
        int n = 5_000;
        return firstToSumInOverNWays(n);
    }

    private static int firstToSumInOverNWays(int n) {
        int number = 1;
        long answer = waysToSumWithPrimes(1);
        while (answer <= n) {
            number++;
            answer = waysToSumWithPrimes(number);
        }
        return number;
    }

    private static long waysToSumWithPrimes(int n) {
        return waysToSumWithPrimes(n, 0);
    }

    private static long waysToSumWithPrimes(int n, int biggestAdderIndex) {
        if (n == 0) return 1;
        if (n == 1) return 0;

        String input = Arrays.toString(new int[]{n, biggestAdderIndex});
        if (waysToSumWithPrimes.containsKey(input)) {
            return waysToSumWithPrimes.get(input);
        }

        long sum = 0;
        for (int i = biggestAdderIndex; i < primes.length && primes[i] <= n; i++) {
            sum += waysToSumWithPrimes(n-primes[i], i);
        }

        waysToSumWithPrimes.put(input, sum);

        return sum;
    }

    private static int[] getPrimesBelow(int limit) {
        return Converter.booleanArrToIntArr(Primes.sieveOfPrimes(limit));
    }
}
