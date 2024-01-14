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

        int i = 1;
        while (productDigits.size() < 9) {
            int[] digits = Converter.digitArray(n*i);
            for (int digit : digits) productDigits.add(digit);
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

            int product = (int) Converter.digitFromArrayLong(concatenatedProduct);

            if (product > biggestProduct) {
                biggestProduct = product;
                biggestFactor = i;
            }
        }

        return new int[] {biggestFactor, biggestProduct};
    }


}
