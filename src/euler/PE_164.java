package euler;

import java.util.Arrays;

public class PE_164 {
    private static long[][][] memo;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int digits = 20;
        long result = numbersWithMaxConsecutiveDigitSum(digits);
        return String.valueOf(result);
    }

    private static long numbersWithMaxConsecutiveDigitSum(int digitCount) {
        memo = new long[digitCount][10][10];
        for (long[][] longs1 : memo) {
            for (long[] longs2 : longs1) {
                Arrays.fill(longs2, -1);
            }
        }
        long count = 0;
        for (int i = 1; i <= 9; i++) {
            int jLimit = 9 - i;
            for (int j = 0; j <= jLimit; j++) {
                count += numbersWithMaxConsecutiveDigitSum(digitCount-2, i, j);
            }
        }
        return count;
    }

    private static long numbersWithMaxConsecutiveDigitSum(int digits, int l1, int l2) {
        if (digits == 0) {
            return 1;
        }

        if (memo[digits][l1][l2] != -1) return memo[digits][l1][l2];

        long count = 0;
        int max = 9 - l1 - l2;
        if (max > 9) max = 9;
        for (int i = 0; i <= max; i++) {
            count += numbersWithMaxConsecutiveDigitSum(digits-1, l2, i);
        }

        memo[digits][l1][l2] = count;
        return count;
    }
}
