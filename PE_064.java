import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_064 {
    public static void main(String[] args) {
        int limit = 10_000;
        System.out.println(countOdds(limit));

        System.out.println(Arrays.toString(getCycle(7)));
    }

    private static int countOdds(int limit) {
        boolean[] squares = makeSquares(limit);
        int counter = 0;

        for (int i = 2; i < limit; i++) {
            if (squares[i]) continue;
            int[] cycle = getCycle(i);
            if (cycle.length % 2 != 0) counter++;
        }

        return counter;
    }

    private static boolean[] makeSquares(int limit) {
        boolean[] squares = new boolean[limit];

        for (int i = 0; i < Math.sqrt(limit); i++) {
            squares[i*i] = true;
        }

        return squares;
    }

    private static int[] convertFraction(int[] fraction) {
        if (fraction.length != 3) throw new RuntimeException("CurrentFraction wrong length: " + Arrays.toString(fraction));
        int numerator = fraction[0];
        int squareRoot = fraction[1];
        int subtractor = fraction[2];
        int interMediateDenominator = squareRoot - subtractor*subtractor;
        int gcd = (int) Divisors.greatestCommonDivisor(numerator, interMediateDenominator);
        interMediateDenominator /= gcd;
        return new int[] {squareRoot, subtractor, interMediateDenominator};
    }

    private static int[] makeNextIteration(int[] convertedFraction) {
        int squareRoot = convertedFraction[0];
        int subtractor = convertedFraction[1];
        int denominator = convertedFraction[2];
        int nextA = (int) ((Math.sqrt(squareRoot) + subtractor) / denominator);
        subtractor -= nextA * denominator;
        return new int[] {nextA, denominator, squareRoot, -subtractor};
    }

    private static int[] nextIterationOfFraction(int[] currentFraction) {
        int[] convertedFraction = convertFraction(currentFraction);
        return makeNextIteration(convertedFraction);
    }

    public static int[] getCycle(int squareRoot) {
        List<Integer> a = new ArrayList<>();
        int[] fraction = makeNextIteration(new int[] {squareRoot, 0, 1});
        List<int[]> existingFractions = new ArrayList<>();

        while (!contains(fraction, existingFractions)) {
            a.add(fraction[0]);
            existingFractions.add(fraction);
            fraction = ArrayFunctions.subArray(fraction, 1, fraction.length-1);

            fraction = nextIterationOfFraction(fraction);
        }

        for (int[] existingFraction : existingFractions) {
            if (Arrays.equals(existingFraction, fraction)) break;
            a.removeFirst();
        }

        return Converter.listToArr(a);
    }

    private static boolean contains(int[] arr, List<int[]> list) {
        for (int[] el : list) {
            if (Arrays.equals(el, arr)) return true;
        }
        return false;
    }
}

