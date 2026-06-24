package euler;

import utils.Diophantine;

public class PE_0086 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int solutionCount = 1_000_000;
        return String.valueOf(smallestLimitFor(solutionCount));
    }

    private static int smallestLimitFor(int limit) {
        int M = 1;
        int solutionCount = 0;
        while (solutionCount < limit) {
            M++;
            solutionCount += solutionCount(M);
        }
        return M;
    }

    private static int solutionCount(int c) {
        long cc = (long) c * c;
        int count = 0;

        int step = 1;
        if (c % 2 != 0) step = 2;
        for (int ab = 2; ab < 2*c; ab+=step) {
            if (Diophantine.root(cc + (long) ab * ab) > 0) {
                count += Math.min(ab-1, c) -  (ab-1)/2;
            }
        }

        return count;
    }
}
