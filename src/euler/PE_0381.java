package euler;

import utils.Diophantine;
import utils.Primes;

public class PE_0381 {
    static void main() {
        System.out.println(PE());
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
        int t = (3*p) % 8;
        return (p * t - 3) / 8;
    }
}
