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
        int[] values = new int[primes.length];
        for (int i = 0; i < primes.length; i++) {
            values[i] = (int) (Math.log(limit) / Math.log(primes[i]));
        }

        long product = 1;
        for (int i = 0; i < primes.length; i++) {
            product *= Diophantine.pow(primes[i], values[i]);
        }
        return product;
    }
}
