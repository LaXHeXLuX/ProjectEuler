package euler;

public class PE_038 {
    public static void main(String[] args) {
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
            int digits = digitAmount(prod);
            for (int j = 0; j < digits; j++) {
                concatenatedProduct *= 10;
            }
            concatenatedProduct += prod;
            i++;
        }

        return concatenatedProduct;
    }

    private static int digitAmount(long n) {
        return (int) (Math.log10(n)) + 1;
    }

    private static long[] biggestPandigitalProduct() {
        long biggestProduct = 0;
        long biggestFactor = 0;

        for (int i = 1; i < 10_000; i++) {
            long concatenatedProduct = concatenatedProductsOf(i);
            if (!isPandigital(concatenatedProduct)) continue;

            if (concatenatedProduct > biggestProduct) {
                biggestProduct = concatenatedProduct;
                biggestFactor = i;
            }
        }

        return new long[] {biggestFactor, biggestProduct};
    }

    private static boolean isPandigital(long n) {
        if (n < 123_456_789 || n > 987_654_321) return false;
        int[] digits = new int[10];
        while (n > 0) {
            digits[Math.toIntExact(n % 10)]++;
            n /= 10;
        }

        if (digits[0] > 0) return false;
        for (int i = 1; i < digits.length; i++) {
            if (digits[i] != 1) return false;
        }
        return true;
    }
}
