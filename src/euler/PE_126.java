package euler;

public class PE_126 {
    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int n = 100;
        return firstWithSolutionCount(n);
    }

    private static int firstWithSolutionCount(int n) {
        int i = 2;
        int result = C(i);
        while (result != n) {
            i++;
            result = C(i);
        }
        return i;
    }

    private static int C(int target) {
        if (target % 2 == 1) return 0;
        int count = 0;
        for (int z = 1; z <= target/4; z++) {
            for (int y = 1; y <= z; y++) {
                for (int x = 1; x <= y; x++) {
                    int n = 1;
                    int coverValue = COVER(n, x, y, z);
                    while (coverValue < target) {
                        n++;
                        coverValue = COVER(n, x, y, z);
                    }
                    if (coverValue == target) count++;
                }
            }
        }
        return count;
    }

    private static int OUT(int n, int x, int y) {
        return 2 * (x + y + 2*n - 2);
    }

    private static int TOP(int n, int x, int y) {
        return 2*(n-1) * (x + y + n - 2) + x*y;
    }

    private static int COVER(int n, int x, int y, int z) {
        return z * OUT(n, x, y) + 2 * TOP(n, x, y);
        //return 2*z*(x + y + 2*n - 2) + 4 * (n-1) * (x+y+n-2) + 2*x*y;
    }
}
