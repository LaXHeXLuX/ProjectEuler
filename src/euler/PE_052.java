package src.euler;

import util.ArrayFunctions;
import util.Combinations;
import util.Converter;

public class PE_052 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int[] multiples = {2, 3, 4, 5, 6};
        return  smallestNumberWithPermutedMultiples(multiples);
    }

    private static int smallestNumberWithPermutedMultiples(int[] multiples) {
        multiples = ArrayFunctions.mergeSort(multiples);
        int biggestMultiple = multiples[multiples.length-1];

        int x = 0;
        while (x >= 0)  {
            x++;
            int[] digits = Converter.digitArray(x);
            int limit = 10/biggestMultiple;
            if (digits[0] > limit) continue;
            if (hasPermutedMultiples(x, multiples)) return x;
        }

        return -1;
    }

    private static boolean hasPermutedMultiples(int n, int[] multiples) {
        int[] digits = Converter.digitArray(n);

        for (int j : multiples) {
            int multiple = n * j;
            if (!Combinations.isPermutation(digits, Converter.digitArray(multiple))) return false;
        }

        return true;
    }
}
