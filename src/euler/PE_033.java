package euler;

import utils.ArrayFunctions;
import utils.Converter;
import utils.Diophantine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_033 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 100;
        List<int[]> curiousFractions = curiousFractions(limit);

        int a = 1;
        int b = 1;
        for (int[] fraction : curiousFractions) {
            a *= fraction[0];
            b *= fraction[1];
        }

        return simplifyFraction(a, b)[1];
    }

    private static List<int[]> curiousFractions(int limit) {
        List<int[]> curiousFractions = new ArrayList<>();

        for (int i = limit/10; i < limit; i++) {
            for (int j = i + 1; j < limit; j++) {
                if (isCuriousFraction(i, j)) curiousFractions.add(new int[] {i, j});
            }
        }

        return curiousFractions;
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
                    int a = (int) Converter.fromDigitArray(digitsA);
                    int b = (int) Converter.fromDigitArray(digitsB);
                    int newA = (int) Converter.fromDigitArray(ArrayFunctions.removeIndex(digitsA, i));
                    int newB = (int) Converter.fromDigitArray(ArrayFunctions.removeIndex(digitsB, j));

                    int[] fraction = simplifyFraction(a, b);
                    int[] newFraction = simplifyFraction(newA, newB);

                    if (Arrays.equals(fraction, newFraction)) return true;
                }
            }
        }
        return false;
    }

    private static int[] simplifyFraction(int a, int b) {
        int gcd = (int) Diophantine.gcd(a, b);
        return new int[] {a/gcd, b/gcd};
    }
}
