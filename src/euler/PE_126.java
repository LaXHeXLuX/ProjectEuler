package euler;

public class PE_126 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 1000;
        return firstWithSolutionCount(n);
    }

    private static int firstWithSolutionCount(int n) {
        int limit = n;
        while (true) {
            int[] solutionCounts = solutionCounts(limit);
            for (int i = 0; i < solutionCounts.length; i++) {
                if (solutionCounts[i] == n) return i;
            }
            limit *= 2;
        }
    }

    private static int[] solutionCounts(int limit) {
        int[] solutionCounts = new int[limit];
        for (int z = 1; z <= limit/4; z++) {
            for (int y = 1; y <= z && FIRST(1, y, z) < limit; y++) {
                for (int x = 1; x <= y && FIRST(x, y, z) < limit; x++) {
                    int n = 1;
                    int coverValue = COVER(n, x, y, z);
                    while (coverValue < limit) {
                        solutionCounts[coverValue]++;
                        n++;
                        coverValue = COVER(n, x, y, z);
                    }
                }
            }
        }
        return solutionCounts;
    }

    private static int FIRST(int x, int y, int z) {
        return 2 * (z * (x + y) + x * y);
    }

    private static int OUT(int n, int x, int y) {
        return 2 * (x + y + 2*n - 2);
    }

    private static int TOP(int n, int x, int y) {
        return 2*(n-1) * (x + y + n - 2) + x*y;
    }

    private static int COVER(int n, int x, int y, int z) {
        return z * OUT(n, x, y) + 2 * TOP(n, x, y);
    }
}
