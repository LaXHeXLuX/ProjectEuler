package euler;

import utils.Diophantine;
import utils.Primes;

public class PE_005 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 20;
        return String.valueOf(firstToDivide(n));
    }

    private static long firstToDivide(int limit) {
        int[] primes = Primes.primes(limit);
        double logLimit = Math.log(limit);
        long product = 1;
        for (int p : primes) {
            product *= Diophantine.pow(p, (int) (logLimit / Math.log(p)));
        }
        return product;
    }
}
