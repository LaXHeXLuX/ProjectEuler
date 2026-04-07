package euler;

import utils.Diophantine;

public class PE_004 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 3;
        return String.valueOf(largestPalindromeNumberMadeFromTwoNDigitNumbers(n));
    }

    private static long largestPalindromeNumberMadeFromTwoNDigitNumbers(int n) {
        long largestProduct = -1;
        int iLimit = (int) Diophantine.pow10[n-1] - 1;
        for (int i = 10*iLimit + 9; i > iLimit; i--) {
            for (int j = i; j > iLimit; j--) {
                long product = (long) i * j;
                if (Diophantine.isPalindrome(product) && product > largestProduct) {
                    largestProduct = product;
                    iLimit = (int) Math.sqrt(largestProduct);
                }
            }
        }
        return largestProduct;
    }
}
