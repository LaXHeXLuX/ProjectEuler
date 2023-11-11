import UsefulFunctions.Converter;
import UsefulFunctions.Pandigital;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_038 {
    public static void main(String[] args) {
        int[] biggest = biggestPandigitalProduct();
        System.out.println(Arrays.toString(biggest));
    }

    private static int[] concatenatedProductsOf(long n) {
        List<Integer> productDigits = new ArrayList<>();
        Converter c = new Converter();

        int i = 1;
        while (productDigits.size() < 9) {
            int[] digits = c.digitArray(n*i);
            for (int digit : digits) productDigits.add(digit);
            i++;
        }

        return c.listToArrInt(productDigits);
    }

    private static int[] biggestPandigitalProduct() {
        int biggestProduct = 0;
        int biggestFactor = 0;

        for (int i = 1; i < 10_000; i++) {
            int[] concatenatedProduct = concatenatedProductsOf(i);
            Pandigital p = new Pandigital();

            if (!p.isPandigital(concatenatedProduct)) continue;

            Converter c = new Converter();
            int product = (int) c.digitFromArrayLong(concatenatedProduct);

            if (product > biggestProduct) {
                biggestProduct = product;
                biggestFactor = i;
            }
        }

        return new int[] {biggestFactor, biggestProduct};
    }


}
