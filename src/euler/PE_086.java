package euler;

import utils.Diophantine;

public class PE_086 {
    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int solutionCount = 1_000_000;
        return smallestLimitFor(solutionCount);
    }

    private static int numberOfIntegerSolutions2(int limit) {
        int sum = 0;
        for (int a = 1; a <= limit; a++) {
            sum += integerSolutionsFor(a);
        }
        return sum;
    }

    private static int integerSolutionsFor(int a) {
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

    private static int smallestLimitFor(int solutionCount) {
        int low = 0;
        int high = 1;
        while (numberOfIntegerSolutions2(high) < solutionCount) {
            low = high;
            high *= 2;
        }
        while (low < high) {
            int mid = (low + high) / 2;
            int solutions = numberOfIntegerSolutions2(mid);
            if (solutions < solutionCount) low = mid + 1;
            else high = mid;
        }

        return low;
    }
}
