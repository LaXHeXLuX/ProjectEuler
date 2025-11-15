package euler;

import utils.Primes;

public class PE_046 {
    private static boolean[] composites;
    private static int[] primesInt;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000_000;
        return smallestConjectureContradiction(limit);
    }

    private static int smallestConjectureContradiction(int limit) {
        composites = Primes.compositeSieve(limit);
        primesInt = Primes.primes(composites);

        for (int i = 9; i < limit; i++) {
            if (!isOddComposite(i)) continue;
            if (!canBeWrittenAsSumOfPrimeAndTwoSquares(i)) return i;
        }

        return -1;
    }

    private static boolean isOddComposite(int n) {
        return (n & 1) == 1 && composites[n >> 1];
    }

    private static boolean canBeWrittenAsSumOfPrimeAndTwoSquares(int n) {
        for (Integer prime : primesInt) {
            int square = 1;
            while (prime + 2*square*square < n) square++;

            if (prime + 2*square*square == n) return true;
        }
        return false;
    }
}
