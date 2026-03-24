package euler.other.leonhard;

import utils.Diophantine;

import java.math.BigInteger;

public class PL_001 {
    private static final BigInteger B0 = BigInteger.ZERO;
    private static final BigInteger B1 = BigInteger.ONE;
    private static final BigInteger B2 = BigInteger.TWO;
    private static final BigInteger B3 = BigInteger.valueOf(3);
    private static final BigInteger B5 = BigInteger.valueOf(5);
    private static final BigInteger B7 = BigInteger.valueOf(7);
    private static final BigInteger B15 = BigInteger.valueOf(15);

    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PL());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }

    public static String PL() {
        for (int n = 29; n < 30; n++) {
            System.out.println(n + ":" + sum35DigitSum(n));
        }
        return null;
    }

    private static int sum35DigitSum(int n) {
        double s = System.currentTimeMillis();
        BigInteger limit = B7.pow((int) Diophantine.pow(2, n));
        double e = System.currentTimeMillis();
        System.out.println("limit: " + (e - s) + " ms");
        BigInteger sum = sum35(limit);
        double e2 = System.currentTimeMillis();
        System.out.println("sum: " + (e2 - e) + " ms");
        int digitSum = Diophantine.digitSum(sum);
        double e3 = System.currentTimeMillis();
        System.out.println("digitSum: " + (e3 - e2) + " ms");
        return digitSum;
    }

    private static BigInteger sum35(BigInteger limit) {
        BigInteger sum = B0;
        sum = sum.add(sum(B3, limit));
        sum = sum.add(sum(B5, limit));
        sum = sum.subtract(sum(B15, limit));
        return sum;
    }

    private static BigInteger sum(BigInteger multiple, BigInteger limit) {
        BigInteger base = limit.divide(multiple);
        return base.multiply(base.add(B1)).divide(B2).multiply(multiple);
    }
}
