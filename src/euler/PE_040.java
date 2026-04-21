package euler;

import utils.Diophantine;

public class PE_040 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long[] indexes = {1, 10, 100, 1_000, 10_000, 100_000, 1_000_000};
        return String.valueOf(productOfConstantDigits(indexes));
    }

    private static long productOfConstantDigits(long[] indexes) {
        long product = 1;
        for (long index : indexes) {
            product *= digitAt(index-1);
        }
        return product;
    }

    private static int digitAt(long i) {
        int digits = 1;
        long numberCount = 9;
        long j = numberCount*digits;
        while (j <= i) {
            i -= j;
            digits++;
            numberCount *= 10;
            j = numberCount*digits;
        }

        long num = Diophantine.pow10[digits-1] + i/digits;
        int digitPlace = digits - 1 - (int) (i % digits);
        return (int) ((num / Diophantine.pow10[digitPlace]) % 10);
    }
}
