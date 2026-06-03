package euler;

import java.math.BigInteger;

public class PE_0063 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int base = 10;
        int result = powerfulDigitCounts(base);
        return String.valueOf(result);
    }

    private static int powerfulDigitCounts(int base) {
        BigInteger bigBase = BigInteger.valueOf(base);
        int solutionCount = base - 1;
        int aLimit = base - 1;

        for (int a = 2; a <= aLimit; a++) {
            int b = 2;
            BigInteger power = power(a, b);
            int digits = digits(power, bigBase);
            while (digits >= b) {
                if (digits == b) solutionCount++;
                b++;
                power = power(a, b);
                digits = digits(power, bigBase);
            }
        }
        return solutionCount;
    }

    private static BigInteger power(int a, int b) {
        BigInteger bigA = BigInteger.valueOf(a);
        return bigA.pow(b);
    }

    private static int digits(BigInteger power, BigInteger base) {
        int digits = 0;
        while (power.compareTo(BigInteger.ZERO) > 0) {
            power = power.divide(base);
            digits++;
        }
        return digits;
    }
}
