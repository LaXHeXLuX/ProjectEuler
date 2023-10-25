import java.util.Arrays;

public class PE7 {
    public static void main(String[] args) {
        System.out.println(nthPrime(10_001));
    }
    private static int nthPrime(int n) {
        int upperBound = upperBoundForNthPrime(n);
        boolean[] primes = sieveOfPrimes(upperBound);
        int counter = 0;
        for (int i = 0; i <= upperBound; i++) {
            if (primes[i]) counter++;
            if (counter == n) return i;
        }
        return -1;
    }
    private static boolean[] sieveOfPrimes(int limit) {
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
    private static void removeProductsOfN(boolean[] primes, int n) {
        int compositeNumber = n*n;
        while (compositeNumber < primes.length) {
            if (compositeNumber < 0) return;
            primes[compositeNumber] = false;
            compositeNumber += n;
        }
    }
    private static int upperBoundForNthPrime(int n) {
        if (n < 6) return 12;
        double logN = Math.log(n);
        return (int) (n*logN + n*Math.log(logN));
    }
}
