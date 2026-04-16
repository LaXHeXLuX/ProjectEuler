package euler;

import utils.Converter;
import utils.Diophantine;

import java.util.ArrayList;
import java.util.List;

public class PE_033 {
    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        for (int d = 2; d < 10; d++) {
            double s = System.currentTimeMillis();
            List<int[]> curiousFractions = curiousFractions(d);
            double e = System.currentTimeMillis();
            System.out.println(d + ": " + curiousFractions.size() + " " + ((int) (e-s)) + " ms");
        }
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
        int limit = (int) Diophantine.pow10[digits];

        int[][] digitArrays = new int[limit][];
        for (int i = limit/10; i < limit; i++) {
            digitArrays[i] = Converter.digitArray(i);
        }

        for (int n = limit/10; n < limit; n++) {
            for (int d = n+1; d < limit; d++) {
                if (curiousFraction(n, d, digitArrays[n], digitArrays[d])) {
                    curiousFractions.add(new int[] {n, d});
                }
            }
        }

        return curiousFractions;
    }

    private static boolean curiousFraction(int n, int d, int[] nd, int[] dd) {
        if (Diophantine.gcd(n, d) == 1) return false;

        int iLimit = nd.length - 1;
        if (nd[iLimit] == 0) iLimit--;
        int jLimit = dd.length - 1;
        if (dd[jLimit] == 0) jLimit--;

        for (int i = 0; i <= iLimit; i++) {
            for (int j = 0; j <= jLimit; j++) {
                if (nd[i] == dd[j]) {
                    int newA = removeDigit(n, nd.length - 1 - i);
                    int newB = removeDigit(d, dd.length - 1 - j);
                    if (n*newB == d*newA) return true;
                }
            }
        }
        return false;
    }
    
    private static int removeDigit(int n, int digit) {
        int newN = 0;
        int pow10 = 1;
        for (int i = 0; i < digit; i++) {
            newN += pow10 * (n % 10);
            pow10 *= 10;
            n /= 10;
        }
        n /= 10;
        while (n > 0) {
            newN += pow10 * (n % 10);
            pow10 *= 10;
            n /= 10;
        }
        return newN;
    }
}
