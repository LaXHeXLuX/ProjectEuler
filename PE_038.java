import util.Converter;
import util.Pandigital;

import java.util.ArrayList;
import java.util.List;

public class PE_038 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int[] biggest = biggestPandigitalProduct();
        return biggest[biggest.length-1];
    }

    private static int[] concatenatedProductsOf(int n) {
        List<Integer> productDigits = new ArrayList<>();

        int i = 1;
        while (productDigits.size() < 9) {
            int temp = n*i;
            while (temp > 0) {
                productDigits.add(temp % 10);
                temp /= 10;
            }
            i++;
        }

        return Converter.listToArr(productDigits);
    }

    private static int[] biggestPandigitalProduct() {
        int biggestProduct = 0;
        int biggestFactor = 0;

        for (int i = 1; i < 10_000; i++) {
            int[] concatenatedProduct = concatenatedProductsOf(i);

            if (!Pandigital.isPandigital(concatenatedProduct)) continue;

            int product = (int) Converter.fromDigitArray(concatenatedProduct);

            if (product > biggestProduct) {
                biggestProduct = product;
                biggestFactor = i;
            }
        }

        return new int[] {biggestFactor, biggestProduct};
    }


}
