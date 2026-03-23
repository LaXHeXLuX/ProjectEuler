package euler;

import utils.Diophantine;

public class PE_039 {
    private static int[] perimeterSolutions;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 1_000;
        solutions(limit);
        return String.valueOf(maxSolutions());
    }

    private static int maxSolutions() {
        int max = 0;
        int maxI = 0;
        for (int i = 0; i < perimeterSolutions.length; i++) {
            if (perimeterSolutions[i] > max) {
                max = perimeterSolutions[i];
                maxI = i;
            }
        }

        return maxI;
    }

    private static void solutions(int limit) {
        perimeterSolutions = new int[limit+1];
        int mLimit = (int) Math.sqrt(limit/2.0);
        for (int m = 2; m <= mLimit; m++) {
            int nLimit = limit/2/m - m;
            if (nLimit > m-1) nLimit = m-1;
            for (int n = 1 + m%2; n <= nLimit; n+=2) {
                if (Diophantine.gcd(m, n) > 1) continue;
                int kLimit = limit/2/m/(m+n);
                for (int k = 1; k <= kLimit; k++) {
                    perimeterSolutions[2*k*m*(m+n)]++;
                }
            }
        }
    }
}
