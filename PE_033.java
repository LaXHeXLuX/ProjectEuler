import UsefulFunctions.ArrayFunctions;
import UsefulFunctions.Converter;
import UsefulFunctions.Divisors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_033 {
    public static void main(String[] args) {
        int limit = 100;
        int[][] curiousFractions = findCuriousFractions(limit);
        for (int[] curiousFraction : curiousFractions) System.out.println(Arrays.toString(curiousFraction));

        int a = 1;
        int b = 1;
        for (int[] fraction : curiousFractions) {
            a *= fraction[0];
            b *= fraction[1];
        }

        System.out.println(simplifyFraction(a, b)[1]);
    }

    private static int[][] findCuriousFractions(int limit) {
        List<int[]> curiousFractions = new ArrayList<>();

        for (int i = limit/10; i < limit; i++) {
            for (int j = i + 1; j < limit; j++) {
                if (isCuriousFraction(i, j)) curiousFractions.add(new int[] {i, j});
            }
        }

        return Converter.arrListToArrInt(curiousFractions);
    }

    private static boolean isCuriousFraction(int a, int b) {
        int[] digitsA = Converter.digitArray(a);
        int[] digitsB = Converter.digitArray(b);

        return isCuriousFraction(digitsA, digitsB);
    }

    private static boolean isCuriousFraction(int[] digitsA, int[] digitsB) {
        for (int i = 0; i < digitsA.length; i++) {
            if (digitsA[i] == 0) continue;
            for (int j = 0; j < digitsB.length; j++) {
                if (digitsA[i] == digitsB[j]) {
                    int a = (int) Converter.digitFromArrayLong(digitsA);
                    int b = (int) Converter.digitFromArrayLong(digitsB);
                    int newA = (int) Converter.digitFromArrayLong(ArrayFunctions.removeIndex(digitsA, i));
                    int newB = (int) Converter.digitFromArrayLong(ArrayFunctions.removeIndex(digitsB, j));

                    int[] fraction = simplifyFraction(a, b);
                    int[] newFraction = simplifyFraction(newA, newB);

                    if (Arrays.equals(fraction, newFraction)) return true;
                }
            }
        }
        return false;
    }

    private static int[] simplifyFraction(int a, int b) {
        int gcd = (int) Divisors.greatestCommonDivisor(a, b);
        return new int[] {a/gcd, b/gcd};
    }
}
