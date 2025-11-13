package euler;

import utils.Diophantine;

import java.util.ArrayList;
import java.util.List;

public class PE_036 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int[] bases = {2, 10};
        int limit = 1_000_000;
        List<Long> palindromes = findPalindromesInBases(limit, bases);

        long sum = 0;
        for (long palindrome : palindromes) sum += palindrome;
        return sum;
    }

    private static boolean isPalindromeInBases(long n, int[] bases) {
        for (int base : bases) {
            if (!Diophantine.isPalindromeInBase(n, base)) return false;
        }
        return true;
    }

    private static List<Long> findPalindromesInBases(long limit, int[] bases) {
        List<Long> palindromes = new ArrayList<>();

        for (long i = 1; i < limit; i++) {
            if (isPalindromeInBases(i, bases)) {
                palindromes.add(i);
            }
        }

        return palindromes;
    }
}
