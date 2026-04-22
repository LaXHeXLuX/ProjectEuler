package euler;

import utils.Primes;

public class PE_058 {

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int[] fraction = {1, 10};
        return String.valueOf(firstSpiralLengthWithPrimeDiagonalRatioBelow(fraction));
    }

    private static long firstSpiralLengthWithPrimeDiagonalRatioBelow(int[] fraction) {
        int num = fraction[0];
        int den = fraction[1];
        long l = 3;
        long primeCounter = 3;
        while (num * 2*l < primeCounter * den) {
            l += 2;
            long ll = l*l;
            if (Primes.isPrime(ll - 3*l + 3)) primeCounter++;
            if (Primes.isPrime(ll - 2*l + 2)) primeCounter++;
            if (Primes.isPrime(ll - l + 1)) primeCounter++;
        }
        return l;
    }
}
