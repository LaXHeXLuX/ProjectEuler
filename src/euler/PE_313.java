package euler;

import utils.Primes;

public class PE_313 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int primeLimit = 1_000_000;
        return numberOfGridsWhereSIsPrimeSquare(primeLimit);
    }

    private static long numberOfGridsWhereSIsPrimeSquare(int primeLimit) {
        long count = 0;

        int[] primes = Primes.primes(primeLimit);
        for (int i = 1; i < primes.length; i++) {
            count += gridAmountWithPrimeSquared(primes[i]);
        }
        return count;
    }

    private static long gridAmountWithPrimeSquared(long prime) {
        long s = prime*prime;
        long mStart = (s + 15) / 8;
        long mLimit = (s + 9) / 6;
        return (mLimit - mStart + 1) * 2;
    }
}
