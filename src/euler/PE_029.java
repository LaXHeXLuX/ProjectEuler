package euler;

import utils.Primes;

import java.util.Arrays;

public class PE_029 {
    private static int[] pow;
    private static int[] largestFactor;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 100;
        return String.valueOf(distinctPowerCount(limit, limit));
    }

    private static void makePow(int n) {
        pow = new int[n+1];
        Arrays.fill(pow, 1);
        int limit = (int) Math.sqrt(n);
        for (int i = 2; i <= limit; i++) {
            if (pow[i] > 1) continue;
            long p = (long) i * i;
            int s = 2;
            while (p < pow.length) {
                pow[Math.toIntExact(p)] = s;
                p *= i;
                s++;
            }
        }
    }

    private static void makeLargestFactor(int n) {
        int limit = (int) (Math.log(n) / Math.log(2));
        largestFactor = new int[limit+1];
        largestFactor[1] = 1;
        for (int i = 2; i < largestFactor.length; i++) {
            Primes.PF[] pfs = Primes.primeFactors(i);
            largestFactor[i] = Math.toIntExact(pfs[pfs.length-1].primeFactor);
        }
    }

    private static long distinctPowerCount(int limitA, int limitB) {
        makePow(limitA);
        makeLargestFactor(limitA);
        boolean[] primes = Primes.sieve(limitB+1);
        primes[2] = false;
        long counter = 0;
        for (int a = 2; a <= limitA; a++) {
            counter += add(limitB, a);
        }
        return counter;
    }

    private static int add(int limitB, int a) {
        int add;
        int gcd = pow[a];
        int gcdLargestDivisor = largestFactor[gcd];

        if (gcd == 1) {
            add = limitB -1;
        }
        else {
            int start = limitB /gcdLargestDivisor + 1;
            if (start < 2) start = 2;
            int end = (gcd-1)* limitB /gcd;
            add = limitB - end;
            for (int b = start; b <= end; b++) {
                int div = biggestDivisorSmallerThan(b*gcd, gcd);
                if (b * gcd / div > limitB) add++;
            }
        }
        return add;
    }

    private static int biggestDivisorSmallerThan(int n, int limit) {
        if (limit > n) return n;
        int i = limit-1;
        while (n % i != 0) i--;
        return i;
    }
}
