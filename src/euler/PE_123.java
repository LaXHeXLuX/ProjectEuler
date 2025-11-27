package euler;

import utils.Primes;

public class PE_123 {
    private static int[] primes;

    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        long limit = 10_000_000_000L;
        int sieveLimit = upperBoundForSieve(limit);
        primes = Primes.primes(sieveLimit);
        return leastNWithRemainderExceeding(limit);
    }

    private static int upperBoundForSieve(long limit) {
        limit /= 2;
        int lowerN = 1;
        int upperN = 2;
        long upperBoundForPrime = (long) upperN * Primes.upperBoundForNthPrime(upperN);
        while (upperBoundForPrime < limit) {
            lowerN = upperN;
            upperN *= 2;
            upperBoundForPrime = (long) upperN * Primes.upperBoundForNthPrime(upperN);
        }
        while (lowerN < upperN) {
            int middleN = (lowerN + upperN) / 2;
            upperBoundForPrime = (long) middleN * Primes.upperBoundForNthPrime(middleN);
            if (upperBoundForPrime < limit) lowerN = middleN+1;
            if (upperBoundForPrime > limit) upperN = middleN;
        }

        return Primes.upperBoundForNthPrime(lowerN);
    }

    private static long leastNWithRemainderExceeding(long limit) {
        int n = 1;

        long rem = remainder(n);
        while (rem <= limit) {
            n+=2;
            rem = remainder(n);
        }
        return n;
    }

    private static long remainder(int n) {
        if (n % 2 == 0) return 2;
        if (n < 5) return new int[] {0, 5}[n / 2];
        long p = primes[n-1];
        return 2*p*n;
    }
}
