import java.math.BigInteger;

public class PE_080 {
    static boolean[] squares;
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 100;
        int precision = 100;
        return totalOfDigitSums(limit, precision);
    }

    private static void makeSquares(int limit) {
        squares = new boolean[limit+1];
        int i = 0;
        int i2 = 0;
        while (i2 < squares.length) {
            squares[i2] = true;
            i++;
            i2 = i*i;
        }
    }

    private static int totalOfDigitSums(int limit, int precision) {
        makeSquares(limit);
        int total = 0;

        for (int i = 0; i <= limit; i++) {
            if (squares[i]) continue;
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
            sum += bi.remainder(BigInteger.TEN).intValue();
            bi = bi.divide(BigInteger.TEN);
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
