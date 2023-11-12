import UsefulFunctions.ArrayFunctions;
import UsefulFunctions.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_026 {
    public static void main(String[] args) {
        int limit = 1001;
        System.out.println(longestReciprocalCycleUnder(limit));
    }

    private static int longestReciprocalCycleUnder(int limit) {
        int longest = 0;
        int longestNumber = 0;
        for (int i = 2; i < limit; i++) {
            int a = 1;
            int[][] reciprocal = reciprocalCycle(a, i);
            if (reciprocal[1].length > longest) {
                longest = reciprocal[1].length;
                longestNumber = i;
                System.out.println(i + ", " + longest + ": " + Arrays.deepToString(reciprocal));
            }
        }
        return longestNumber;
    }

    private static int[][] reciprocalCycle(int a, int b) {
        if (a <= 0) throw new RuntimeException("a has to be more than 0");
        if (b <= 0) throw new RuntimeException("b has to be more than 0");
        if (b == 1) return new int[][] {};
        List<Integer> reciprocalCycle = new ArrayList<>();
        List<Integer> modCycle = new ArrayList<>();
        int divisible = a;

        while (!ArrayFunctions.contains(divisible, modCycle) && divisible != 0) {
            modCycle.add(divisible);
            divisible *= 10;
            while (divisible < b) {
                modCycle.add(divisible);
                divisible *= 10;
                reciprocalCycle.add(0);
            }
            reciprocalCycle.add(divisible / b);
            divisible = divisible % b;
        }

        return convertToFraction(divisible, Converter.listToArrInt(modCycle), Converter.listToArrInt(reciprocalCycle));
    }

    private static int[][] convertToFraction(int divisible, int[] modCycle, int[] reciprocalCycle) {
        int i;
        for (i = 0; i < modCycle.length; i++) {
            if (modCycle[i] == divisible) {
                break;
            }
        }

        int[] nonCycle = new int[i];
        int[] cycle = new int[reciprocalCycle.length-i];
        System.arraycopy(reciprocalCycle, 0, nonCycle, 0, nonCycle.length);
        System.arraycopy(reciprocalCycle, i, cycle, 0, cycle.length);
        return new int[][] {nonCycle, cycle};
    }
}
