package euler;

import utils.Diophantine;
import utils.Primes;

public class PE_381 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int[] primes = Primes.primes(100_000_000);
        long sum = 4+4+1+11+6+2+14;
        for (int i = 9; i < primes.length; i++) {
            int p = primes[i];
            sum += S(p);
        }
        return String.valueOf(sum);
    }

    private static int S(int p) {
        return (9 * modDivide(p-24, p)) % p;
    }

    private static int modDivide(int a, int mod) {
        int[] sol = Diophantine.extendedEuclidean(a, mod);
        int result = sol[0] % mod;
        if (result < 0) result += mod;
        return result;
    }
}
