package euler;

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

    private static boolean isPalindromeInBase(long n, int base) {
        if (base == 2) {
            int left = 63 - Long.numberOfLeadingZeros(n);
            int right = 0;
            while (right < left) {
                if (((n >> left) & 1) != ((n >> right) & 1))
                    return false;
                left--;
                right++;
            }
            return true;
        }
        int[] convertedDigits = new int[(int)(Math.log(n)/Math.log(base))+1];
        int limit = convertedDigits.length / 2;
        for (int i = 0; i < limit; i++) {
            convertedDigits[convertedDigits.length-i-1] = (int) (n%base);
            n /= base;
        }
        if (convertedDigits.length % 2 == 1) n /= base;
        limit = convertedDigits.length - limit;
        for (int i = limit; i < convertedDigits.length; i++) {
            if ((int) (n%base) != convertedDigits[i]) return false;
            n /= base;
        }
        return true;
    }

    private static boolean isPalindromeInBases(long n, int[] bases) {
        for (int base : bases) {
            if (!isPalindromeInBase(n, base)) return false;
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
