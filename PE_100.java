import java.math.BigInteger;

public class PE_100 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        long lowerLimit = 1_000_000_000_000L;
        long[] result = firstArrangementAfter(lowerLimit);
        return result[1];
    }

    private static long[] firstArrangementAfter(long lowerLimit) {
        long aLimit = lowerLimit * 2 + 1;
        long[] ab = {1, 1};
        while (ab[0] <= aLimit) {
            ab = nextAB(ab);
        }

        long y = (1 + ab[0]) / 2;
        BigInteger bigY = BigInteger.valueOf(y);
        BigInteger square = BigInteger.TWO.multiply(bigY).multiply(bigY.subtract(BigInteger.ONE)).add(BigInteger.ONE);
        BigInteger[] sqrtRem = square.sqrtAndRemainder();
        if (sqrtRem[1].compareTo(BigInteger.ZERO) != 0) throw new RuntimeException(square + " isn't a perfect square!");
        long x = (1 + sqrtRem[0].longValue()) / 2;

        return new long[] {y, x};
    }

    private static long[] nextAB(long[] ab) {
        return new long[] {3*ab[0] + 4*ab[1], 2*ab[0] + 3*ab[1]};
    }
}
