import java.util.HashMap;
import java.util.Map;

public class PE_086 {
    private static final Map<Long, Integer> squares = new HashMap<>();
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        makeSquares();
        int solutionCount = 1_000_000;
        int limit = smallestLimitFor(solutionCount);
        System.out.println(STR."\{solutionCount}: \{limit}");

        long end = System.currentTimeMillis();
        System.out.println(STR."Time: \{end - start} ms");
    }

    private static void makeSquares() {
        for (int i = 1; i < 1_000_000; i++) {
            squares.put((long) i * i, i);
        }
    }
    private static long cuboidRouteSquare(int aPlusB, int c) {
        return (long) (aPlusB) * (aPlusB) + (long) c * c;
    }
    private static boolean routeIsInteger(int aPlusB, int c) {
        return squares.containsKey(cuboidRouteSquare(aPlusB, c));
    }
    private static int numberOfIntegerSolutions(int limit) {
        int solutions = 0;

        for (int c = 1; c <= limit; c++) {
            for (int ab = 1; ab <= limit*2; ab++) {
                if (routeIsInteger(ab, c)) {
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
        int limit = 0;
        while (numberOfIntegerSolutions(limit) <= solutionCount) {
            limit += 100;
        }

        limit -= 10;

        while (numberOfIntegerSolutions(limit) > solutionCount) {
            limit -= 10;
        }

        limit++;

        while (numberOfIntegerSolutions(limit) <= solutionCount) {
            limit++;
        }

        return limit;
    }
}
