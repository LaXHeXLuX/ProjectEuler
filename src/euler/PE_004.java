package euler;

import utils.Diophantine;

public class PE_004 {
    static void main() {
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
                if (Diophantine.isPalindrome(product) && product > largestProduct) {
                    largestProduct = product;
                    iLimit = (int) Math.pow(largestProduct, 0.5);
                }
            }
        }
        return largestProduct;
    }
}
