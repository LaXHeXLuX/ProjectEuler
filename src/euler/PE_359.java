package euler;

import utils.Diophantine;
import utils.Primes;

import java.math.BigInteger;

public class PE_359 {
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
            return P(f, r, mod);
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

    private static long P(long f, long r, long mod) {
        BigInteger bR = BigInteger.valueOf(r);
        BigInteger bMod = BigInteger.valueOf(mod);
        if (f == 1) {
            BigInteger result = bR.multiply(bR.add(BigInteger.ONE)).divide(BigInteger.TWO);
            return result.mod(bMod).longValueExact();
        }
        BigInteger bF = BigInteger.valueOf(f);
        if (r == 2) {
            BigInteger result = bF.pow(2).divide(BigInteger.TWO).add(BigInteger.ONE);
            if (f % 2 == 0) result = result.add(BigInteger.TWO.multiply(bF));
            return result.mod(bMod).longValueExact();
        }
        BigInteger s1 = BigInteger.ONE;
        BigInteger s2 = BigInteger.TWO.multiply(bF);
        if ((f & 1) == 0) {
            s1 = BigInteger.TWO.multiply(bF).add(BigInteger.ONE);
            s2 = BigInteger.TWO;
        }
        BigInteger p = bF.pow(2).divide(BigInteger.TWO);
        p = p.add(bR.subtract(BigInteger.valueOf(3)).multiply(bR.subtract(BigInteger.ONE)).divide(BigInteger.TWO));
        p = p.add(bR.divide(BigInteger.TWO).multiply(s1.add(s2)));
        if ((r & 1) == 0) p = p.subtract(s2).add(BigInteger.ONE);
        return p.mod(bMod).longValueExact();
    }
}