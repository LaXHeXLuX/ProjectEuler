package euler;

import utils.ArrayFunctions;
import utils.Converter;
import utils.Diophantine;

import java.util.ArrayList;
import java.util.List;

public class PE_033 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int digits = 2;
        List<int[]> curiousFractions = curiousFractions(digits);

        long a = 1;
        long b = 1;
        for (int[] fraction : curiousFractions) {
            a *= fraction[0];
            b *= fraction[1];
            long gcd = Diophantine.gcd(a, b);
            a /= gcd;
            b /= gcd;
        }

        return String.valueOf(b);
    }

    private static List<int[]> curiousFractions(int digits) {
        List<int[]> curiousFractions = new ArrayList<>();
        
        int limit = (int) Diophantine.pow(10, digits);
        for (int i = limit/10; i < limit; i++) {
            for (int j = i+1; j < limit; j++) {
                if (curiousFraction(i, j)) curiousFractions.add(new int[] {i, j});
            }
        }

        return curiousFractions;
    }

    private static boolean curiousFraction(int i, int j) {
        if (Diophantine.gcd(i, j) == 1) return false;

        int[] di = Converter.digitArray(i);
        int[] dj = Converter.digitArray(j);

        return curiousFraction(di, dj);
    }

    private static boolean curiousFraction(int[] d1, int[] d2) {
        int a = (int) Converter.fromDigitArray(d1);
        int b = (int) Converter.fromDigitArray(d2);
        for (int i = 0; i < d1.length; i++) {
            if (d1[i] == 0) continue;
            for (int j = 0; j < d2.length; j++) {
                if (d1[i] == d2[j]) {
                    int newA = (int) Converter.fromDigitArray(ArrayFunctions.removeIndex(d1, i));
                    int newB = (int) Converter.fromDigitArray(ArrayFunctions.removeIndex(d2, j));
                    if (a*newB == b*newA) return true;
                }
            }
        }
        return false;
    }
}
