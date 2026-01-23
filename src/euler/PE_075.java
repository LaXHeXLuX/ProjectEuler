package euler;

import utils.Diophantine;

public class PE_075 {
    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int perimeterLimit = 1_500_000;
        int solutionCount = 1;
        return perimetersWithRightTriangleSolutions(solutionCount, perimeterLimit);
    }
    
    private static int perimetersWithRightTriangleSolutions(int solutionCount, int perimeterLimit) {
        int[] perimeterSolutionCounts = new int[perimeterLimit+1];

        int mLimit = (int) Math.sqrt(perimeterLimit);
        for (int m = 2; m <= mLimit; m++) {
            for (int n = m-1; n > 0; n -= 2) {
                if (Diophantine.gcd(m, n) > 1) continue;
                int P = 2*m*(m+n);
                int k = 1;
                while (k*P <= perimeterLimit) {
                    perimeterSolutionCounts[k*P]++;
                    k++;
                }
            }
        }

        int count = 0;
        for (int perimeterSolutionCount : perimeterSolutionCounts) {
            if (perimeterSolutionCount == solutionCount) count++;
        }
        return count;
    }
}
