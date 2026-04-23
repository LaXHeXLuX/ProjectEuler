package euler;

import utils.Diophantine;
import utils.Divisors;
import utils.Primes;

public class PE_044 {
    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        int count = 1;
        double s = System.currentTimeMillis();
        System.out.println(pentagonalDifferences(count));
        double e = System.currentTimeMillis();
        System.out.println("1: " + (e-s) + " ms");
        return String.valueOf(smallestDifferenceWithProperty());
    }

    private static long pentagonalDifferences(int count) {
        int found = 0;

        long y = -1;
        while (found != count) {
            y += 6;
            long product = (y-1)*(y+1);
            Primes.PF[] pfs = Primes.primeFactorsProduct(y+1, 1, y-1);
            if (pfs[0].power <= pfs[1].power) continue;
            long pow6 = Diophantine.pow(6, pfs[1].power);
            //long exp2 = pfs[0].power - pfs[1].power;
            pfs[0].power -= pfs[1].power;
            pfs[1].power = 0;

            long[] divisors = Divisors.divisors(pfs);
            int kLimit = (int) (Math.sqrt(product) / pow6/* / (1L << exp2)*/);
            for (long k : divisors) {
                if (k > kLimit) break;
//                long d1;
//                long d2;
//                if (pow6 == 6) {
//                    for (int i = 0; i < exp2-2; i++) {
//                        d1 = pow6 * (1L << i);
//                        d2 = product / d1;
//                        if (pentagonalDifference(y, d1, d2)) found++;
//                    }
//                }
//                else {
//                    d1 = pow6 * (1L << (exp2-1));
//                    d2 = product / d1;
//                    if (pentagonalDifference(y, d1, d2)) found++;
//                }
                long d1 = pow6 * k;
                long d2 = product / d1;
                if (pentagonalDifference(y, d1, d2)) found++;
            }
        }

        return -1;
    }

    private static boolean pentagonalDifference(long y, long d1, long d2) {
        if (d2 % 2 != 0) return false; //throw new RuntimeException("BAD");
        if (d2 % 6 != 4) return false;
        long sum = d1+d2;
        if (sum % 12 != 10) return false; //throw new RuntimeException("sum = d1 + d2: " + d1 + " + " + d2 + " = " + sum + " % 12 = " + (sum % 12) + ". y p: " + y + " " + product);
        long x = (d1 + d2)/2;
        long m = d2 - x;
        if (x % 6 != 5) throw new RuntimeException("x: " + x + " % 6 = " + (x%6) + ". y: " + y + ", d1 d2: " + d1 + " " + d2);
        if (m % 6 != 5) throw new RuntimeException("m: " + m + " % 6 = " + (m%6) + ". y: " + y + ", d1 d2: " + d1 + " " + d2);
        long nD = (1+y)/6;
        long n1 = (1+m)/6;
        long n2 = (1+x)/6;
        long P3 = pentagonal(n1) + pentagonal(n2);
        if (isPentagonal(P3)) {
            System.out.println("found " + nD + " " + n1 + " " + n2 + ": " + pentagonal(nD) + " " + pentagonal(n1) + " " + pentagonal(n2));
            return true;
        }
        return false;
    }

    private static long pentagonal(long n) {
        return n*(3*n - 1) / 2;
    }

    private static boolean isPentagonal(long x) {
        long n = (long) ((Math.sqrt(24 * x + 1) + 1) / 6);
        return pentagonal(n) == x;
    }

    private static long smallestDifferenceWithProperty() {
        long smallestDiff = Long.MAX_VALUE;
        for (int n2 = 2; ; n2++) {
            long p2 = pentagonal(n2);
            for (int n1 = n2-1; n1 > 0; n1--) {
                long p1 = pentagonal(n1);
                if (p1 >= smallestDiff && p2 >= smallestDiff) break;
                if (isPentagonal(p1 + p2)) {
                    if (isPentagonal(p1 + 2 * p2) && p1 < smallestDiff) smallestDiff = p1;
                    if (isPentagonal(2 * p1 + p2) && p2 < smallestDiff) smallestDiff = p2;
                }
            }
            if (3L*n2 - 2 > smallestDiff) return smallestDiff;
        }
    }
}
