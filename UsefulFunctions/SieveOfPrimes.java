package UsefulFunctions;

import java.util.Arrays;

public class SieveOfPrimes {
    public static boolean[] sieveOfPrimes(int limit) {
        boolean[] primes = new boolean[limit];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        primes[2] = true;
        removeProductsOfN(primes, 2);
        for (int i = 3; i < Math.pow(primes.length, 0.5)+1; i+=2) {
            if (!primes[i]) continue;
            removeProductsOfN(primes, i);
        }
        return primes;
    }
    public static void removeProductsOfN(boolean[] primes, int n) {
        int compositeNumber = n*n;
        while (compositeNumber < primes.length) {
            if (compositeNumber < 0) return;
            primes[compositeNumber] = false;
            compositeNumber += n;
        }
    }
}
