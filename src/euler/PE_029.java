package euler;

import utils.Diophantine;
import utils.Primes;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class PE_029 {
    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        int limit = 100;
        return String.valueOf(distinctPowerCount(limit, limit));
    }

    private static long distinctPowerCount(int limitA, int limitB) {
        boolean[] primes = Primes.sieve(limitB+1);
        primes[2] = false;
        long counter = 0;
        for (int a = 2; a <= limitA; a++) {
            int add;
            Primes.PF[] pfs = Primes.primeFactors(a);
            int gcd = gcd(pfs);
            int gcdSmallestDivisor = 1;
            if (gcd > 1) gcdSmallestDivisor = Math.toIntExact(Primes.primeFactors(gcd)[0].primeFactor);
            int gcdLargestDivisor = gcdSmallestDivisor;
            if (gcdSmallestDivisor != gcd) gcdLargestDivisor = gcd / gcdSmallestDivisor;
            if (gcd == 1) {
                add = limitB-1;
            }
            else {
                int start = limitB/gcdLargestDivisor + 1;
                if (start < 2) start = 2;
                int end = (gcd-1)*limitB/gcd;
                add = limitB - end;
                for (int b = start; b <= end; b++) {
                    int div = biggestDivisorSmallerThan(b*gcd, gcd);
                    if (b * gcd / div > limitB) add++;
                }
            }
            counter += add;
            //System.out.println("a: " + a + ", gcd: " + gcd + ", smallest: " + gcdSmallestDivisor + ", largest: " + gcdLargestDivisor + ", adding: " + add);
        }
        return counter;
    }

    private static int biggestDivisorSmallerThan(int n, int limit) {
        if (limit > n) return n;
        int i = limit-1;
        while (n % i != 0) i--;
        return i;
    }

    private static int gcd(Primes.PF[] pfs) {
        int gcd = pfs[0].power;
        for (int i = 1; i < pfs.length; i++) {
            gcd = Diophantine.gcd(gcd, pfs[i].power);
        }
        return gcd;
    }

    private static long distinctPowerCount0(int limitA, int limitB) {
        Set<BigInteger> powers = new HashSet<>();
        long counter = 0;
        for (int a = 2; a <= limitA; a++) {
            int add = 0;
            for (int b = 2; b <= limitB; b++) {
                BigInteger bi = BigInteger.valueOf(a).pow(b);
                if (!powers.contains(bi)) {
                    add++;
                    powers.add(bi);
                }
            }
            System.out.println("a: " + a + ", adding " + add);
            counter += add;
        }
        return counter;
    }
}
