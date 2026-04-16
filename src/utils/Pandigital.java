package utils;

public class Pandigital {
    public static boolean isPandigital(long n) {
        return isPandigital(n, 10);
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
    public static boolean groupIsPandigital(int[] group) {
        return groupIsPandigital(group, 10);
    }
    public static boolean groupIsPandigital(int[] group, int base) {
        boolean[] digits = new boolean[base-1];
        for (int i : group) {
            while (i > 0) {
                int digit = i % base;
                if (digit == 0 || digits[digit-1]) return false;
                digits[digit-1] = true;
                i /= base;
            }
        }
        for (boolean digit : digits) {
            if (!digit) return false;
        }
        return true;
    }
}
