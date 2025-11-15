package utils;

public class Pandigital {

    public static boolean isPandigital(long n) {
        int[] digits = new int[10];
        while (n > 0) {
            digits[Math.toIntExact(n % 10)]++;
            n /= 10;
        }

        if (digits[0] > 0) return false;
        int i;
        for (i = 1; i < digits.length; i++) {
            if (digits[i] != 1) break;
        }
        for (; i < digits.length; i++) {
            if (digits[i] != 0) return false;
        }
        return true;
    }
    public static boolean isPandigital(long n, int startDigit, int endDigit) {
        if (n == 0) return startDigit == 0 && endDigit == 0;
        if ((int) Math.log10(n) != endDigit-startDigit) return false;
        int[] digits = new int[10];
        while (n > 0) {
            digits[Math.toIntExact(n % 10)]++;
            n /= 10;
        }

        for (int i = startDigit; i <= endDigit; i++) {
            if (digits[i] != 1) return false;
        }
        return true;
    }
    public static boolean groupIsPandigital(int[] group) {
        boolean[] digits = new boolean[9];
        for (int i : group) {
            while (i > 0) {
                int digit = i % 10;
                if (digit == 0 || digits[digit-1]) return false;
                digits[digit-1] = true;
                i /= 10;
            }
        }
        for (boolean digit : digits) {
            if (!digit) return false;
        }
        return true;
    }
}
