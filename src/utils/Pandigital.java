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
}
