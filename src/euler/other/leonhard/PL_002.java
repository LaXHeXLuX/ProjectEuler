package euler.other.leonhard;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class PL_002 {
    private static final MathContext MC = new MathContext(50, RoundingMode.HALF_UP);
    private static final BigDecimal SQRT5_2 = BigDecimal.valueOf(5).sqrt(MC);
    private static final BigDecimal PHI_2 = SQRT5_2.add(BigDecimal.ONE).divide(BigDecimal.TWO, MC);
    private static final BigDecimal LOG_PHI_10_BD = ln(BigDecimal.TEN).divide(ln(PHI_2), MC);
    private static final BigDecimal LOG_PHI_SQRT5_BD = ln(SQRT5_2).divide(ln(PHI_2), MC);

    private static final int mod = 1_000_000_007;
    private static int duplicateLength;

    private static BigDecimal ln(BigDecimal x) {
        // Argument reduction: ln(x) = n*ln(2) + ln(x / 2^n)
        // Reduce x into [1, 2) so the series converges quickly
        int n = 0;
        BigDecimal two = BigDecimal.valueOf(2);
        while (x.compareTo(two) >= 0) { x = x.divide(two, MC); n++; }
        while (x.compareTo(BigDecimal.ONE) < 0) { x = x.multiply(two, MC); n--; }

        BigDecimal result = getBigDecimal(x);

        // Add back n*ln(2) — compute ln(2) recursively (only ~50 iterations since 2 is in range)
        if (n != 0) {
            BigDecimal ln2 = lnTwo();
            result = result.add(BigDecimal.valueOf(n).multiply(ln2, MC));
        }
        return result;
    }
    private static BigDecimal getBigDecimal(BigDecimal x) {
        BigDecimal y = x.subtract(BigDecimal.ONE)
                .divide(x.add(BigDecimal.ONE), MC);
        BigDecimal y2 = y.multiply(y, MC);
        BigDecimal term = y;
        BigDecimal sum  = y;
        for (int k = 1; k <= 1000; k++) {
            term = term.multiply(y2, MC);
            BigDecimal delta = term.divide(BigDecimal.valueOf(2*k + 1), MC);
            sum = sum.add(delta);
            if (delta.abs().compareTo(BigDecimal.ONE.movePointLeft(MC.getPrecision() - 2)) < 0)
                break;
        }
        return sum.multiply(BigDecimal.valueOf(2), MC);
    }
    private static BigDecimal lnTwo() {
        BigDecimal y  = BigDecimal.ONE.divide(BigDecimal.valueOf(3), MC); // (2-1)/(2+1)
        BigDecimal y2 = y.multiply(y, MC);
        BigDecimal term = y, sum = y;
        for (int k = 1; k <= 1000; k++) {
            term = term.multiply(y2, MC);
            BigDecimal delta = term.divide(BigDecimal.valueOf(2*k + 1), MC);
            sum = sum.add(delta);
            if (delta.abs().compareTo(BigDecimal.ONE.movePointLeft(MC.getPrecision() - 2)) < 0)
                break;
        }
        return sum.multiply(BigDecimal.valueOf(2), MC);
    }

    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PL());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }

    public static String PL() {
        findFirstDuplicate();
        for (int n = 1; n < 101; n++) {
            System.out.print(n + ":" + sumOfEvenFibonacciNumbers2(n) + ",");
        }
        return null;
    }

    private static int limit2(int n) {
        BigDecimal pow2n = BigDecimal.valueOf(2).pow(n);
        BigDecimal number = BigDecimal.valueOf(9).multiply(pow2n).multiply(LOG_PHI_10_BD, MC).add(LOG_PHI_SQRT5_BD);
        BigDecimal result = number.divide(BigDecimal.valueOf(3), MC);
        return result.remainder(BigDecimal.valueOf(duplicateLength)).intValue();
    }

    private static int sumOfEvenFibonacciNumbers2(int n) {
        long initial = limit2(n);

        int a = 1;
        int b = 1;
        int c = a+b;
        int sum = 0;
        for (int i = 0; i < initial; i++) {
            sum = (sum + c) % mod;
            a = (c+b) % mod;
            b = (a+c) % mod;
            c = (a+b) % mod;
        }
        return sum;
    }

    private static void findFirstDuplicate() {
        int a = 1;
        int b = 1;
        int c = a+b;
        int sum = 0;
        int i = 1;
        while (true) {
            sum = (sum + c) % mod;
            a = (c+b) % mod;
            b = (a+c) % mod;
            c = (a+b) % mod;

            if (a == 1 && b == 1) {
                duplicateLength = i;
                return;
            }
            i++;
        }
    }
}
