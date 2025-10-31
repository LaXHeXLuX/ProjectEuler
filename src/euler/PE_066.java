package euler;

import java.math.BigInteger;

public class PE_066 {
    private static boolean[] squares;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        squares = makeSquares();
        int limit = 1000;
        return maxMinimalSolution(limit);
    }

    private static BigInteger[] getNthConvergent(int squareRoot, int[] cycle, int n) {
        BigInteger first = BigInteger.valueOf((long) Math.sqrt(squareRoot));
        if (n == 1) return new BigInteger[] {first, BigInteger.ONE};
        n--;
        int cycleIndex = (n-1) % cycle.length;
        BigInteger[] fraction = new BigInteger[] {BigInteger.ONE, BigInteger.valueOf(cycle[cycleIndex])};

        while (n > 1) {
            cycleIndex--;
            if (cycleIndex < 0) cycleIndex = cycle.length-1;
            fraction = addToFraction(BigInteger.valueOf(cycle[cycleIndex]), fraction);
            reverseFraction(fraction);
            n--;
        }

        return addToFraction(first, fraction);
    }

    private static BigInteger[] addToFraction(BigInteger adder, BigInteger[] fraction) {
        return new BigInteger[] {fraction[0].add((adder.multiply(fraction[1]))), fraction[1]};
    }

    private static void reverseFraction(BigInteger[] fraction) {
        BigInteger temp = fraction[0];
        fraction[0] = fraction[1];
        fraction[1] = temp;
    }

    private static int maxMinimalSolution(int limit) {
        BigInteger max = BigInteger.ZERO;
        int maxD = -1;

        for (int D = 1; D <= limit; D++) {
            if (squares[D]) continue;
            BigInteger minimalSolution = minimalSolutionForX(D);
            if (minimalSolution.compareTo(max) > 0) {
                max = minimalSolution;
                maxD = D;
            }
        }

        return maxD;
    }

    private static boolean[] makeSquares() {
        boolean[] squares = new boolean[Integer.MAX_VALUE-10];

        for (int i = 1; i < Math.sqrt(squares.length); i++) {
            squares[i*i] = true;
        }

        return squares;
    }

    private static BigInteger minimalSolutionForX(int D) {
        if (squares[D]) return BigInteger.ZERO;
        int[] cycle = PE_064.getCycle(D);
        int n = 1;
        BigInteger[] fraction = getNthConvergent(D, cycle, n);
        while (!equationWorks(fraction, D)) {
            n++;
            fraction = getNthConvergent(D, cycle, n);
        }
        return fraction[0];
    }

    private static boolean equationWorks(BigInteger[] fraction, int D) {
        BigInteger bigXSquared = fraction[0].pow(2);
        BigInteger bigYSquared = fraction[1].pow(2);
        BigInteger bigD = BigInteger.valueOf(D);
        BigInteger product = bigYSquared.multiply(bigD);

        return bigXSquared.subtract(product).compareTo(BigInteger.ONE) == 0;
    }
}
