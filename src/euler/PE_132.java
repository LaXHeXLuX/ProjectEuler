package euler;

import utils.Primes;

public class PE_132 {
    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int factorCount = 40;
        int n = 9;
        long[] factors = firstFactorsForPower10Repunit(n, factorCount);
        return sum(factors);
    }

    private static long sum(long[] arr) {
        long s = 0;
        for (long l : arr) s += l;
        return s;
    }

    private static long[] firstFactorsForPower10Repunit(int n, int factorCount) {
        long[] factors = new long[factorCount];
        int index = 0;
        int p = 1;
        while (true) {
            p += 2;
            if (!Primes.isPrime(p)) continue;
            if (ord10Divides(n, p)) {
                factors[index] = p;
                index++;
                if (index >= factorCount) break;
            }
        }

        return factors;
    }

    private static long powMod(long exp, long mod) {
        long result = 1;
        long base = 10 % mod;

        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % mod;

            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    private static boolean ord10Divides(int n, long p) {
        if (p == 2 || p == 3 || p == 5) return false;
        long e = powMod(n, p - 1);
        return powMod(e, p) == 1;
    }
}
