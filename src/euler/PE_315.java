package euler;


import utils.Primes;

import java.util.Arrays;

public class PE_315 {
    private static final boolean[][] segments = {
            {true, true, true, false, true, true, true},
            {false, false, true, false, false, true, false},
            {true, false, true, true, true, false, true},
            {true, false, true, true, false, true, true},
            {false, true, true, true, false, true, false},
            {true, true, false, true, false, true, true},
            {true, true, false, true, true, true, true},
            {true, true, true, false, false, true, false},
            {true, true, true, true, true, true, true},
            {true, true, true, true, false, true, true},
    };
    private static final int[][] segmentOverlap = new int[10][10];
    private static int[] memoizedSaves;

    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        makeSegments();
        int primesStart = 10_000_000;
        int primesEnd = 2*primesStart;
        makeMemoizedSaves((int) Math.log10(primesEnd) + 1);
        return totalSavesOverPrimesBetween(primesStart, primesEnd);
    }

    private static long totalSavesOverPrimesBetween(int start, int end) {
        int[] primes = Primes.primes(end);
        int startIndex = Arrays.binarySearch(primes, start);
        if (startIndex >= 0) startIndex++;
        else startIndex = -startIndex - 1;
        long totalSaves = 0;
        for (int i = startIndex; i < primes.length; i++) {
            int result = digitalRootTransitionSaves(primes[i]);
            totalSaves += result;
        }
        return totalSaves;
    }

    private static int digitalRootTransitionSaves(int n) {
        if (n < memoizedSaves.length) return memoizedSaves[n];

        int sum = 0;
        int[] digits = new int[(int) Math.log10(n) + 1];
        for (int i = 0; i < digits.length; i++) {
            int digit = n % 10;
            digits[digits.length - i - 1] = digit;
            sum += digit;
            n /= 10;
        }

        n = sum;
        int[] sumDigits = new int[(int) Math.log10(n) + 1];
        for (int i = 0; i < sumDigits.length; i++) {
            int digit = n % 10;
            sumDigits[sumDigits.length - i - 1] = digit;
            n /= 10;
        }

        return 2*totalSegmentsOverlap(digits, sumDigits) + memoizedSaves[sum];
    }

    private static int digitalRootTransitionSavesHelper(int n) {
        int saves = 0;

        int sum = 0;
        int[] digits = new int[(int) Math.log10(n) + 1];
        for (int i = 0; i < digits.length; i++) {
            int digit = n % 10;
            digits[digits.length - i - 1] = digit;
            sum += digit;
            n /= 10;
        }

        while (n != sum) {
            n = sum;
            int[] sumDigits = new int[(int) Math.log10(n) + 1];
            sum = 0;
            int temp = n;
            for (int i = 0; i < sumDigits.length; i++) {
                int digit = temp % 10;
                sumDigits[sumDigits.length - i - 1] = digit;
                sum += digit;
                temp /= 10;
            }
            saves += totalSegmentsOverlap(digits, sumDigits);
            digits = sumDigits;
        }

        return 2*saves;
    }

    private static void makeMemoizedSaves(int maxDigitCount) {
        int maxN = maxDigitCount * 9;
        memoizedSaves = new int[maxN+1];
        for (int i = 10; i <= maxN; i++) {
            memoizedSaves[i] = digitalRootTransitionSavesHelper(i);
        }
    }

    private static void makeSegments() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                segmentOverlap[i][j] = segmentOverlap(segments[i], segments[j]);
            }
        }
    }

    private static int totalSegmentsOverlap(int[] digits1, int[] digits2) {
        int overlap = 0;
        for (int i = 0; i < digits2.length; i++) {
            overlap += segmentOverlap[digits1[digits1.length - digits2.length + i]][digits2[i]];
        }
        return overlap;
    }

    private static int segmentOverlap(boolean[] s1, boolean[] s2) {
        int overlap = 0;
        for (int i = 0; i < 7; i++) {
            if (s1[i] && s2[i]) overlap++;
        }
        return overlap;
    }
}
