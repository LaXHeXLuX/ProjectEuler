package euler;

import utils.Pandigital;

public class PE_038 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long[] biggest = biggestPandigitalProduct();
        return String.valueOf(biggest[biggest.length-1]);
    }

    private static long concatenatedProduct(long n) {
        long concatenatedProduct = 0;

        int i = 1;
        while (concatenatedProduct < 123_456_789) {
            long prod = n * i;
            int digits = (int) (Math.log10(prod)) + 1;
            for (int j = 0; j < digits; j++) {
                concatenatedProduct *= 10;
            }
            concatenatedProduct += prod;
            i++;
        }

        return concatenatedProduct;
    }

    private static long[] biggestPandigitalProduct() {
        long biggestProduct = 0;
        long biggestFactor = 0;

        for (int i = 1; i < 10_000; i++) {
            long concatenatedProduct = concatenatedProduct(i);
            if (!Pandigital.isPandigital(concatenatedProduct)) continue;

            if (concatenatedProduct > biggestProduct) {
                biggestProduct = concatenatedProduct;
                biggestFactor = i;
            }
        }

        return new long[] {biggestFactor, biggestProduct};
    }
}
