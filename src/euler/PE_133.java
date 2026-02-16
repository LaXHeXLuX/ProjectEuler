package euler;

import utils.Diophantine;
import utils.Primes;

public class PE_133 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 100_000;
        int[] primes = Primes.primes(limit);

        long sum = 0;
        for (int prime : primes) {
            if (!ord10Divides(prime)) {
                sum += prime;
            }
        }

        return sum;
    }

    private static boolean ord10Divides(int p) {
        if (p == 2 || p == 3 || p == 5) return false;
        
        int i = p-1;
        
        Primes.PF[] pfs = Primes.primeFactors(i);
        for (Primes.PF pf : pfs) {
            int q = Math.toIntExact(pf.primeFactor);
            while (i % q == 0 && Diophantine.powMod(10, i / q, p) == 1) {
                i /= q;
            }
        }

        while (i % 2 == 0) i /= 2;
        while (i % 5 == 0) i /= 5;

        return i == 1;
    }
}
