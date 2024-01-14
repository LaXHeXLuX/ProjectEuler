import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_036 {
    public static void main(String[] args) {
        int[] bases = {2, 10};
        int limit = 1_000_000;
        long[] palindromes = findPalindromesInBases(limit, bases);

        long sum = 0;
        for (long palindrome : palindromes) sum += palindrome;
        System.out.println(Arrays.toString(palindromes));
        System.out.println(sum);
    }

    private static boolean isPalindrome(int[] digitArray) {
        for (int i = 0; i < digitArray.length/2; i++) {
            if (digitArray[i] != digitArray[digitArray.length-i-1]) return false;
        }
        return true;
    }

    private static boolean isPalindromeInBases(long n, int[] bases) {
        for (int base : bases) {
            int[] baseArray = Converter.convertToBase(n, base);
            if (!isPalindrome(baseArray)) return false;
        }

        return true;
    }

    private static long[] findPalindromesInBases(long limit, int[] bases) {
        List<Long> palindromes = new ArrayList<>();

        for (long i = 0; i < limit; i++) {
            if (isPalindromeInBases(i, bases)) palindromes.add(i);
        }

        return Converter.listToArr(palindromes);
    }
}
