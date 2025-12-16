package euler;

import utils.Primes;

import java.util.Arrays;

public class PE_132 {
    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int factorCount = 35;
        int n = 9;

        long[] factors = firstFactorsForPower10Repunit(n, factorCount);
        System.out.println(Arrays.toString(factors));

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
        long p = 9;
        while (index < factorCount) {
            p += 2;
            if (Primes.isPrime(p) && ord10Divides(n, p)) {
                factors[index] = p;
                index++;
            }
        }

        return factors;
    }

    private static boolean ord10Divides(int n, long p) {
        long mod = 10 % p;
        long power = 1;
        while (mod != 1) {
            power++;
            mod = 10*mod % p;
            //if (power > n) return false;
        }
        long ord = power;

        int count2 = n;
        int count5 = n;
        while (ord % 2 == 0) {
            count2--;
            ord /= 2;
        }
        while (ord % 5 == 0) {
            count5--;
            ord /= 5;
        }
        return count2 >= 0 && count5 >= 0 && ord == 1;
    }
}
