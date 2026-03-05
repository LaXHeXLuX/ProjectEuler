package euler;

import utils.Diophantine;

public class PE_086 {
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

    private static int solutionCount(int a) {
        int step = 1;
        if (a % 2 != 0) step = 2;
        int count = 0;
        for (int bPlusC = 2; bPlusC < a+1; bPlusC += step) {
            if (Diophantine.root(a*a + bPlusC*bPlusC) > 0) {
                count += bPlusC / 2;
            }
        }
        for (int bPlusC = a+1; bPlusC <= 2*a; bPlusC += step) {
            if (Diophantine.root(a*a + bPlusC*bPlusC) > 0) {
                count += (2*a + 2 - bPlusC) / 2;
            }
        }
        return count;
    }
}
