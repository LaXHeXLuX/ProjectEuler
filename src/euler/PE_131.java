package euler;

import utils.Diophantine;
import utils.Primes;

public class PE_131 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000_000;
        return countPrimes(limit);
    }

    private static int countPrimes(int limit) {
        int[] primes = Primes.primes(limit);
        int count = 0;
        for (int i = 2; i < primes.length; i++) {
            int p = primes[i];
            long n = nFor2(p);
            if (n > 0) count++;
        }
        return count;
    }

//    private static long nFor2(long p) {
//
//    }

    private static long nFor2(int p) {
        for (int i = 1; i*i < p; i++) {
            int k = i*i;
            long sqrt = 4L*k*p - 3L*k*k;
            long root = Diophantine.root(sqrt);
            if (root < 0) continue;
            long result = 3L*k*k + k*root;
            long div = 2L*(p-3L*k);
            if (result % div != 0) continue;
            return result / div;
        }
        return -1;
    }
}
