public class PE_041 {
    public static void main(String[] args) {
        System.out.println(largestPandigitalPrime());
    }

    private static int largestPandigitalPrime() {
        int limit = 1_000_000_000;
        boolean[] primes = Primes.sieveOfPrimes(limit);
        System.out.println("sieve done");

        for (int i = limit-1; i > 0; i--) {
            if (!primes[i]) continue;
            if (!Pandigital.isPandigital(i)) continue;
            return i;
        }
        return -1;
    }
}
