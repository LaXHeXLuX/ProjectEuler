package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Primes {
    public static boolean[] sieveOfPrimes(int limit) {
        boolean[] primes = new boolean[limit];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for (int j = 4; j < limit; j += 2) {
            primes[j] = false;
        }
        for (int j = 9; j < limit; j += 6) {
            primes[j] = false;
        }
        int l = (int) Math.sqrt(primes.length);
        for (int i = 6; i <= l; i+=6) {
            for (int x = i-1; x <= i+1; x+=2) {
                if (primes[x]) {
                    for (int j = x * x; j < limit; j += 2*x) {
                        primes[j] = false;
                    }
                }
            }
        }
        return primes;
    }
    public static long[] findPrimeFactors(long n) {
        List<Long> primeFactors = new ArrayList<>();

        while (n % 2 == 0) {
            primeFactors.add(2L);
            n /= 2;
        }

        while (n % 3 == 0) {
            primeFactors.add(3L);
            n /= 3;
        }

        long limit = (long) Math.sqrt(n);
        for (long i = 5; i <= limit; i += 6) {
            while (n % i == 0) {
                primeFactors.add(i);
                n /= i;
                limit = (long) Math.sqrt(n);
            }
            while (n % (i+2) == 0) {
                primeFactors.add(i+2);
                n /= i+2;
                limit = (long) Math.sqrt(n);
            }
        }

        if (n > 1) primeFactors.add(n);

        if (primeFactors.isEmpty()) return new long[0];
        return Converter.listToArr(primeFactors);
    }
    public static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        long limit = (long) Math.sqrt(n);
        for (long i = 5; i <= limit; i+=6) {
            if (n % i == 0 || n % (i+2) == 0) return false;
        }

        return true;
    }
    public static int nthPrime(int n) {
        int upperBound = upperBoundForNthPrime(n);
        boolean[] primes = sieveOfPrimes(upperBound+1);
        int counter = 0;

        for (int i = 0; i <= upperBound; i++) {
            if (primes[i]) counter++;
            if (counter == n) return i;
        }

        return -1;
    }
    private static int upperBoundForNthPrime(int n) {
        if (n < 6) return 12;
        double logN = Math.log(n);
        return (int) (n*logN + n*Math.log(logN));
    }
    public static boolean areRelativePrimes(long n1, long n2) {
        return Divisors.greatestCommonDivisor(n1, n2) == 1;
    }
    public static boolean areRelativePrimes(long[] primes1, long[] primes2) {
        return ArrayFunctions.commonElements(primes1, primes2).length == 0;
    }
    public static long eulersTotient(long n) {
        if (n == 1) return 0;
        long[] primesFactors = findPrimeFactors(n);
        return eulersTotient(primesFactors);
    }
    public static long eulersTotient(long[] primeFactors) {
        long totient = (primeFactors[0]-1);
        for (int i = 1; i < primeFactors.length; i++) {
            if (primeFactors[i] == primeFactors[i-1]) totient *= primeFactors[i];
            else totient *= primeFactors[i]-1;
        }
        return totient;
    }
}
