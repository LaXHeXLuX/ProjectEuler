package euler;

import utils.Diophantine;
import utils.Divisors;
import utils.Primes;

public class PE_044 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int count = 1;
        long result = pentagonalDifferences(count);
        return String.valueOf(result);
    }

    private static long pentagonalDifferences(int count) {
        int found = 0;
        long i = 0;
        while (i >= 0) {
            i++;
            long PD = pentagonal(i);
            long a = 6* i - 1;
            Primes.PF[] pfs = Primes.primeFactorsProduct(a/2 + 1, 1, a/2);
            if (pfs[0].primeFactor != 2) continue;
            long pow3 = Diophantine.pow(3, pfs[1].power);
            pfs[1].power = 0;
            long product = (a/2)*(a/2 + 1) / pow3;
            long[] divisors = Divisors.divisors(pfs);
            long d1Limit = (long) Math.sqrt((double) product / pow3);
            for (long d1 : divisors) {
                if (d1 > d1Limit) break;
                long d2 = product / d1;
                d1 *= pow3;
                long c = d1+d2;
                if (c % 6 != 5) continue;
                long b = d2-d1;
                if (b % 6 != 5) continue;

                long j = (1 + b) / 6;
                long k = (1 + c) / 6;
                long P1 = pentagonal(j);
                long P2 = pentagonal(k);
                long P3 = P1 + P2;
                if (isPentagonal(P3)) {
                    if (++found == count) return PD;
                }
            }
        }
        return -1;
    }

    private static long pentagonal(long n) {
        return n*(3*n - 1) / 2;
    }

    private static boolean isPentagonal(long P) {
        long n = (long) ((Math.sqrt(24 * P + 1) + 1) / 6);
        return pentagonal(n) == P;
    }
}
