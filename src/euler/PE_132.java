package euler;

import utils.Primes;

import java.math.BigInteger;
import java.util.*;

public class PE_132 {
    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int factorCount = 40;

        for (int i = 1; i <= 5; i++) {
            long[] factors = firstFactorsForPower10Repunit(i, factorCount);
            System.out.println(i + ": " + sum(factors) + " - " + Arrays.toString(factors));
        }

        return -1;
    }

    private static long sum(long[] arr) {
        long s = 0;
        for (long l : arr) s += l;
        return s;
    }

    private static long pow10(int p) {
        long pow = 1;
        for (int i = 0; i < p; i++) {
            pow *= 10;
        }
        return pow;
    }

    private static long ord10(long p) {
        long mod = 10 % p;
        long power = 1;
        while (mod != 1) {
            power++;
            mod = 10*mod % p;
        }
        return power;
    }

    private static long[] firstFactorsForPower10Repunit(int n, int factorCount) {
        BigInteger repunit = new BigInteger("1".repeat((int) pow10(n)));
        System.out.println("repunit done: " + n);

        long[] factors = new long[factorCount];
        int index = 0;
        long k = 0;
        long p;
        while (index < factorCount && repunit.compareTo(BigInteger.ONE) > 0 && k < 1_000) {
            k++;
            p = 10*k + 1;
            if (!Primes.isPrime(p)) continue;
            BigInteger bigP = BigInteger.valueOf(p);
            BigInteger[] divRem = repunit.divideAndRemainder(bigP);
            if (divRem[1].equals(BigInteger.ZERO)) {
                factors[index] = p;
                index++;
                repunit = divRem[0];
            }
        }

        return factors;
    }
}
