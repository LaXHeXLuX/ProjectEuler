package euler;

import utils.Combinations;
import utils.Converter;
import utils.Pandigital;
import utils.Primes;

public class PE_041 {
    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        return String.valueOf(largestPandigitalPrime2());
    }

    private static int largestPandigitalPrime2() {
        int[] digits = {1, 2, 3, 4, 5, 6, 7};
        int i = (int) Combinations.FACTORIAL[7];
        int p = (int) Converter.fromDigitArray(Combinations.nthPermutation(digits, i));
        while (!Primes.isPrime(p)) {
            i--;
            p = (int) Converter.fromDigitArray(Combinations.nthPermutation(digits, i));
        }
        return p;
    }
}
