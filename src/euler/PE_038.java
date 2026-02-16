package euler;

import utils.Pandigital;

public class PE_038 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        long[] biggest = biggestPandigitalProduct();
        return biggest[biggest.length-1];
    }

    private static long concatenatedProductsOf(long n) {
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
            long concatenatedProduct = concatenatedProductsOf(i);
            if (!Pandigital.isPandigital(concatenatedProduct)) continue;

            if (concatenatedProduct > biggestProduct) {
                biggestProduct = concatenatedProduct;
                biggestFactor = i;
            }
        }

        return new long[] {biggestFactor, biggestProduct};
    }
}
