package euler;

import utils.Diophantine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_036 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int[] bases = {2, 10};
        int limit = 1_000_000;
        List<Long> palindromes = palindromesInBases(limit, bases);
        return String.valueOf(sum(palindromes));
    }

    private static long sum(List<Long> list) {
        long sum = 0;
        for (Long l : list) sum += l;
        return sum;
    }

    private static boolean isPalindromeInBases(long n, int[] bases) {
        for (int i = 0; i < bases.length-1; i++) {
            if (!Diophantine.isPalindromeInBase(n, bases[i])) return false;
        }
        return true;
    }

    private static List<Long> palindromesInBases(long limit, int[] bases) {
        Arrays.sort(bases);
        List<Long> palindromes = new ArrayList<>();

        long iLimit = (long) Math.sqrt(limit);
        for (long i = 1; i < iLimit; i++) {
            long n = palindromeFrom((int) i, bases[bases.length-1], false);
            if (n >= limit) break;
            if (isPalindromeInBases(n, bases)) {
                palindromes.add(n);
            }
            n = palindromeFrom((int) i, bases[bases.length-1], true);
            if (n < limit && isPalindromeInBases(n, bases)) {
                palindromes.add(n);
            }
        }

        return palindromes;
    }

    private static long palindromeFrom(int n, int base, boolean extra) {
        long palindrome = n;
        if (extra) palindrome = palindrome*base + n%base;
        n /= base;
        while (n > 0) {
            palindrome = palindrome*base + n%base;
            n /= base;
        }
        return palindrome;
    }
}
