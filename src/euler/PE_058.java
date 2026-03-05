package euler;

import utils.Primes;

import java.util.Arrays;

public class PE_058 {

    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }

    public static String PE() {
        int[] fraction = {1, 10};
        return String.valueOf(firstSpiralLengthWithPrimeDiagonalRatioBelow(fraction));
    }

    private static int firstSpiralLengthWithPrimeDiagonalRatioBelow(int[] fraction) {
        int num = fraction[0];
        int den = fraction[1];
        int l = 3;
        int primeCounter = 3;
        while (num * 2*l < primeCounter * den) {
            l += 2;
            int ll = l*l;
            if (l % 3 != 0 && Primes.isPrime(ll - 3L*l + 3)) primeCounter++;
            if (Primes.isPrime(ll - 2L*l + 2)) primeCounter++;
            if (l % 3 != 2 && Primes.isPrime(ll - l + 1)) primeCounter++;
        }
        return l;
    }
}
