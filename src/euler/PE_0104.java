package euler;

import utils.Pandigital;

public class PE_0104 {
    private static final double LOG_GOLDEN = Math.log10((1 + Math.sqrt(5))/2);
    private static final double LOG_SQRT5 = Math.log10(5) / 2;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        return String.valueOf(firstFibonacciWithProperty());
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
            if (Pandigital.isPandigital(f2)) {
                if (Pandigital.isPandigital(first9DigitsOfFibonacciN(counter))) return counter;
            }
        }
    }

    private static int first9DigitsOfFibonacciN(int n) {
        double logFn = n * LOG_GOLDEN - LOG_SQRT5;
        double exp = logFn - Math.floor(logFn) + 8;
        return (int) Math.pow(10, exp);
    }
}
