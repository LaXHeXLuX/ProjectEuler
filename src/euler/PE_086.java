package src.euler;

public class PE_086 {

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int solutionCount = 1_000_000;
        return smallestLimitFor(solutionCount);
    }

    private static boolean isSquare(long n) {
        long root = (long) Math.sqrt(n);
        return root * root == n;
    }

    private static long cuboidRouteSquare(int aPlusB, int c) {
        return (long) (aPlusB) * (aPlusB) + (long) c * c;
    }

    private static int numberOfIntegerSolutions(int limit) {
        int solutions = 0;

        for (int c = 1; c <= limit; c++) {
            for (int ab = 1; ab <= limit*2; ab++) {
                if (isSquare(cuboidRouteSquare(ab, c))) {
                    int toAdd = c - (ab+1)/2 + 1;
                    if (toAdd < 0) toAdd = 0;
                    if (c >= ab) toAdd = ab/2;
                    solutions += toAdd;
                }
            }
        }

        return solutions;
    }

    private static int smallestLimitFor(int solutionCount) {
        int lowerBound = 0;
        int limit = 1;
        int numberOfSolutions = numberOfIntegerSolutions(limit);
        while (numberOfSolutions < solutionCount) {
            lowerBound = limit;
            limit *= 2;
            numberOfSolutions = numberOfIntegerSolutions(limit);
        }
        int upperBound = limit;
        while (lowerBound < upperBound) {
            limit = (lowerBound + upperBound) / 2 + 1;
            numberOfSolutions = numberOfIntegerSolutions(limit);
            if (numberOfSolutions < solutionCount) lowerBound = limit;
            else if (numberOfSolutions > solutionCount) {
                upperBound = limit-1;
                if (lowerBound == upperBound) return limit;
            }
            else return limit;
        }

        return lowerBound + 1;
    }
}
