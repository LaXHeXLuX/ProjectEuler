import java.math.BigInteger;

public class PE_057 {
    public static void main(String[] args) {
        int limit = 1000;
        System.out.println(countExceedsUnder(limit));
    }

    private static int countExceedsUnder(int limit) {
        int counter = 0;

        BigInteger[] fraction = {BigInteger.valueOf(3), BigInteger.TWO};
        for (int i = 1; i < limit; i++) {
            if (numeratorExceedsDenominator(fraction)) counter++;
            fraction = nextIterationOfSquareRootOfTwo(fraction);
        }

        return counter;
    }

    private static boolean numeratorExceedsDenominator(BigInteger[] fraction) {
        int[] digitsNumerator = Converter.digitArray(fraction[0]);
        int[] digitsDenominator = Converter.digitArray(fraction[1]);
        return digitsNumerator.length > digitsDenominator.length;
    }

    private static BigInteger[] nextIterationOfSquareRootOfTwo(BigInteger[] previousIteration) {
        previousIteration[0] = previousIteration[0].subtract(previousIteration[1]);
        BigInteger[] nextIteration = new BigInteger[2];
        nextIteration[1] = previousIteration[0].add(previousIteration[1].multiply(BigInteger.TWO));
        nextIteration[0] = previousIteration[1];
        nextIteration[0] = nextIteration[0].add(nextIteration[1]);
        return nextIteration;
    }
}
