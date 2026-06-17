package euler;

import utils.Diophantine;
import utils.Primes;

import java.math.BigInteger;

public class PE_0359 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long fr = 71328803586048L;
        int digits = 8;
        long mod = Diophantine.pow10[digits];
        return String.valueOf(sumOfAll(fr, mod));
    }

    private static long sumOfAll(long fr, long mod) {
        Primes.PF[] pfs = Primes.primeFactors(fr);
        return sumOfAll(1, 1, 0, pfs, mod);
    }

    private static long sumOfAll(long f, long r, int i, Primes.PF[] pfs, long mod) {
        if (i == pfs.length) {
            return P2(f, r, mod);
        }

        long sum = 0;
        long p = pfs[i].primeFactor;
        for (int j = 0; j <= pfs[i].power; j++) {
            long newF = f * Diophantine.pow(p, j);
            long newR = r * Diophantine.pow(p, pfs[i].power - j);
            sum = (sum + sumOfAll(newF, newR, i+1, pfs, mod)) % mod;
        }
        return sum % mod;
    }

    private static long P2(long f, long r, long mod) {
        BigInteger R = BigInteger.valueOf(r);
        BigInteger M = BigInteger.valueOf(mod);
        if (f == 1) return T(R).mod(M).longValueExact();

        long fOdd = f/2*2 + 1;
        BigInteger F = BigInteger.valueOf(fOdd);

        BigInteger P = F.pow(2).divide(BigInteger.TWO);
        P = P.add(R.divide(BigInteger.TWO).pow(2));
        BigInteger halfR = R.subtract(BigInteger.ONE).divide(BigInteger.TWO);
        P = P.add(BigInteger.TWO.multiply(F.subtract(BigInteger.ONE).multiply(halfR).add(T(halfR))));
        P = P.mod(M);
        long p = P.longValueExact();

        if (f % 2 == 1) return p;
        f = f % mod;
        if (r % 2 == 0) return (p + f) % mod;
        return (p - f + mod) % mod;
    }

    private static BigInteger T(BigInteger n) {
        return n.multiply(n.add(BigInteger.ONE)).divide(BigInteger.TWO);
    }
}