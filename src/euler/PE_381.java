package euler;

import utils.Diophantine;
import utils.Primes;

public class PE_381 {
    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }

    public static String PE() {
        int[] primes = Primes.primes(100_000_000);
        long sum = 0;
        for (int i = 2; i < primes.length; i++) {
            int p = primes[i];
            sum += S(p);
        }
        return String.valueOf(sum);
    }

    private static int S(int p) {
        int a = (int) (((((p-2L)*(p-3)) % p) * (p-4)) % p);
        int P5 = modDivide(a, p);
        return (9 * P5) % p;
    }

    public static int modDivide(int a, int mod) {
        int[] sol = Diophantine.extendedEuclidean(a, mod);
        int result = sol[0] % mod;
        if (result < 0) result += mod;
        return result;
    }
}
