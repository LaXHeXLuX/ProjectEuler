package euler;

import java.math.BigInteger;

public class PE_080 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 100;
        int precision = 100;
        return totalOfDigitSums(limit, precision);
    }

    private static boolean isSquare(long n) {
        long root = (long) Math.sqrt(n);
        return root * root == n;
    }

    private static int totalOfDigitSums(int limit, int precision) {
        int total = 0;

        for (int i = 0; i <= limit; i++) {
            if (isSquare(i)) continue;
            total += digitSumOfSquareRootOf(i, precision);
        }

        return total;
    }

    private static int digitSumOfSquareRootOf(int n, int precision) {
        BigInteger squareRoot = squareRoot(n, precision);
        BigInteger power10 = BigInteger.TEN.pow(precision);
        while (squareRoot.compareTo(power10) >= 0) {
            squareRoot = squareRoot.divide(BigInteger.TEN);
        }
        return digitSum(squareRoot);
    }

    private static int digitSum(BigInteger bi) {
        int sum = 0;

        while (!bi.equals(BigInteger.ZERO)) {
            BigInteger[] divRem = bi.divideAndRemainder(BigInteger.TEN);
            sum += divRem[1].intValue();
            bi = divRem[0];
        }

        return sum;
    }

    private static BigInteger squareRoot(int n, int precision) {
        BigInteger number = BigInteger.valueOf(n).multiply(BigInteger.TEN.pow(precision * 2));
        BigInteger old = number;
        BigInteger root = heron(old, number);

        while (!root.equals(old)) {
            old = root;
            root = heron(old, number);
        }

        return root;
    }

    private static BigInteger heron(BigInteger x, BigInteger a) {
        return x.add(a.divide(x)).divide(BigInteger.TWO);
    }
}
