package euler;

import utils.Converter;
import utils.Primes;

public class PE_046 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000_000;
        return smallestConjectureContradiction(limit);
    }

    private static int smallestConjectureContradiction(int limit) {
        boolean[] primes = Primes.sieveOfPrimes(limit);
        int[] primesArr = Converter.booleanArrToIntArr(primes);

        for (int i = 9; i < limit; i++) {
            if (!isOddComposite(i, primes)) continue;
            if (!canBeWrittenAsSumOfPrimeAndTwoSquares(i, primesArr)) return i;
        }

        return -1;
    }

    private static boolean isOddComposite(int n, boolean[] primes) {
        return n % 2 == 1 && !primes[n];
    }

    private static boolean canBeWrittenAsSumOfPrimeAndTwoSquares(int n, int[] primes) {
        for (int i = 0; i < primes.length && primes[i] < n; i++) {
            int prime = primes[i];

            int square = 1;
            while (prime + 2*square*square < n) square++;

            if (prime + 2*square*square == n) return true;
        }
        return false;
    }
}
