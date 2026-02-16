package euler;

import utils.Primes;

import java.util.HashSet;
import java.util.Set;

public class PE_293 {
    private static final Set<Integer> pseudoFortunate = new HashSet<>();
    private static final int[] primes = Primes.primes(100);

    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        long limit = 1_000_000_000;
        makeAdmissibleNumbers(limit);
        return sum();
    }

    private static long sum() {
        long sum = 0;
        for (Integer i : PE_293.pseudoFortunate) sum += i;
        return sum;
    }

    private static void makeAdmissibleNumbers(long admissibleNumberLimit) {
        makeAdmissibleNumbers(admissibleNumberLimit, 1, 0);
    }

    private static void makeAdmissibleNumbers(long limit, long n, int pi) {
        if (n > limit) return;

        long newLimit = limit / primes[pi];
        while (n <= newLimit) {
            n *= primes[pi];
            pseudoFortunate.add(M(n));
            makeAdmissibleNumbers(limit, n, pi+1);
        }
    }

    private static int M(long n) {
        int m = 3;
        while (!Primes.isPrime(n + m)) {
            m += 2;
        }
        return m;
    }
}
