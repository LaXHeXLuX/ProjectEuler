import util.Pandigital;
import util.Primes;

public class PE_041 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        return largestPandigitalPrime();
    }

    private static int largestPandigitalPrime() {
        int limit = 10_000_000; // 1 through 9 (as well as 1 through 8) would divide 3
        boolean[] primes = Primes.sieveOfPrimes(limit);

        for (int i = limit-1; i > 0; i--) {
            if (!primes[i]) continue;
            if (!Pandigital.isPandigital(i)) continue;
            return i;
        }
        return -1;
    }
}
