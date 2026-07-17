package euler;

import utils.Diophantine;
import utils.Divisors;
import utils.Primes;

public class PE_0358 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int start = Math.toIntExact(Diophantine.pow10[11] / 138);
        int end = Math.toIntExact(Diophantine.pow10[11] / 137);

        int last5 = 56789;
        int residue = Diophantine.modDivide(last5, -1, 100_000);

        start = start / 100_000 * 100_000 + residue;
        for (int i = start; i <= end; i += 100_000) {
            if (!Primes.isPrime(i)) continue;
            int periodSize = periodSize(i);
            if (periodSize != i-1) continue;
            long digitSum = (i-1)/2 * 9L;
            return String.valueOf(digitSum);
        }

        return null;
    }

    private static int periodSize(int n) {
        long[] divs = Divisors.divisors((long) (n-1));
        for (long div : divs) {
            if (Diophantine.powModExact(10, div, n) == 1) return (int) div;
        }
        return -1;
    }
}
