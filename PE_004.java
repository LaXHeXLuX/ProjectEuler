public class PE_004 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 3;
        return largestPalindromeNumberMadeFromTwoNDigitNumbers(n);
    }

    private static long largestPalindromeNumberMadeFromTwoNDigitNumbers(long n) {
        long largestProduct = -1;
        int iLimit = (int) Math.pow(10, n-1)-1;
        for (int i = (int) Math.pow(10, n)-1; i > iLimit; i--) {
            for (int j = i; j > (int) Math.pow(10, n-1)-1; j--) {
                long product = (long) i * j;
                if (isPalindrome(product) && product > largestProduct) {
                    largestProduct = product;
                    iLimit = (int) Math.pow(largestProduct, 0.5);
                }
            }
        }
        return largestProduct;
    }

    private static boolean isPalindrome(long n) { // note for the future: doing it with string isn't that much faster, maybe like 1.5x faster for bigger numbers
        String string = String.valueOf(n);
        return (string.contentEquals(new StringBuilder(string).reverse()));
    }
}
