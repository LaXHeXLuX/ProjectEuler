package euler;

public class PE_104 {
    private static final double LOG_GOLDEN = Math.log10((1 + Math.sqrt(5))/2);
    private static final double LOG_SQRT5 = Math.log10(5) / 2;
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        return firstFibonacciWithProperty();
    }

    private static int firstFibonacciWithProperty() {
        int f1 = 1;
        int f2 = 1;
        int counter = 2;
        while (true) {
            int temp = f2;
            f2 = (f2 + f1) % 1_000_000_000;
            f1 = temp;
            counter++;
            if (isPandigital(f2)) {
                if (isPandigital(first9DigitsOfFibonacciN(counter))) return counter;
            }
        }
    }

    private static int first9DigitsOfFibonacciN(int n) {
        double logFn = n * LOG_GOLDEN - LOG_SQRT5;
        double exp = logFn - Math.floor(logFn) + 8;
        return (int) Math.pow(10, exp);
    }

    private static boolean isPandigital(int n) {
        if (n < 123456789) return false;
        boolean[] digits = new boolean[9];
        while (n > 0) {
            int digit = n % 10;
            if (digit == 0 || digits[digit-1]) return false;
            digits[digit-1] = true;
            n /= 10;
        }
        return true;
    }
}
