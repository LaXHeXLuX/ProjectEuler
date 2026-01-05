package euler;

import utils.Diophantine;
import utils.Divisors;
import utils.Primes;

public class PE_132 {
    public static void main(String[] args) {
        System.out.println(PE());
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

    private static int ord10(int p) {
        int i = p-1;

        int[] divisors = Divisors.divisors(i);
        for (int j = 1; j < divisors.length-1; j++) {
            if (Diophantine.powMod(10, divisors[j], p) == 1) return divisors[j];
        }

        return i;
    }

    private static boolean ord10Divides(int n, int p) {
        if (p == 2 || p == 3 || p == 5) return false;
        int ord = ord10(p);
        int count2 = 0;
        int count5 = 0;
        while (ord % 2 == 0) {
            count2++;
            ord /= 2;
        }
        while (ord % 5 == 0) {
            count5++;
            ord /= 5;
        }
        return ord == 1 && count2 <= n && count5 <= n;
    }
}
