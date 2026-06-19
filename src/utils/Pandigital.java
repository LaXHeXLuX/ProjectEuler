package utils;

public class Pandigital {
    public static boolean isPandigital(long n) {
        if (n < 123456789 || n > 987654321) return false;
        boolean[] digits = new boolean[9];
        while (n > 0) {
            int digit = (int) n % 10;
            if (digit == 0 || digits[digit-1]) return false;
            digits[digit-1] = true;
            n /= 10;
        }
        return true;
    }
    public static boolean isPandigital(long n, int base) {
        int[] digits = new int[base];
        while (n > 0) {
            digits[(int) (n % base)]++;
            n /= base;
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
}
