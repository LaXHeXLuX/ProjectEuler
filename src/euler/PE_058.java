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

    private static int firstSpiralLengthWithPrimeDiagonalRatioBelow(int[] fraction) {
        int num = fraction[0];
        int den = fraction[1];
        int l = 3;
        int primeCounter = 3;
        while (num * 2*l < primeCounter * den) {
            l += 2;
            int ll = l*l;
            if (l % 3 != 0 && Primes.isPrime(ll - 3*l + 3)) primeCounter++;
            if ((l % 5) < 3 && Primes.isPrime(ll - 2*l + 2)) primeCounter++;
            if (l % 3 != 2 && Primes.isPrime(ll - l + 1)) primeCounter++;
        }
        return l;
    }
}
