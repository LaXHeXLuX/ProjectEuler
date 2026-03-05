package euler;

import utils.Diophantine;

import java.math.BigInteger;

public class PE_080 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 100;
        int precision = 100;
        return String.valueOf(totalOfDigitSums(limit, precision));
    }

    private static int totalOfDigitSums(int limit, int precision) {
        int total = 0;
        for (int i = 0; i <= limit; i++) {
            if (Diophantine.root(i) >= 0) continue;
            total += digitSumOfSquareRoot(i, precision);
        }
        return total;
    }

    private static int digitSumOfSquareRoot(int n, int precision) {
        BigInteger sqrt = BigInteger.valueOf(n).multiply(BigInteger.TEN.pow(2*(precision-1))).sqrt();
        return Diophantine.digitSum(sqrt);
    }
}
