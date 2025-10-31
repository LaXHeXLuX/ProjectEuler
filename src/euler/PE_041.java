package euler;

import utils.Pandigital;
import utils.Primes;

public class PE_041 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        return largestPandigitalPrime();
    }

    private static int largestPandigitalPrime() {
        int limit = 7_654_321; // 1 through 9 (as well as 1 through 8) would divide 3
        boolean[] primes = Primes.sieveOfPrimes(limit+1);

        for (int i = limit; i > 0; i-=2) {
            if (!primes[i]) continue;
            if (!Pandigital.isPandigital(i)) continue;
            return i;
        }
        return -1;
    }
}
