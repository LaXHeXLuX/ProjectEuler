package euler;

import utils.Diophantine;

public class PE_091 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 50;
        return String.valueOf(rightTrianglesBelow(n));
    }

    private static long rightTrianglesBelow(int n) {
        long count = 3L*n*n;
        for (int x2 = 1; x2 <= n; x2++) {
            for (int x1 = 1; x1 < x2; x1++) {
                int y2 = Diophantine.root(x1*(x2-x1));
                if (y2 > 0) count += 2;
            }
        }
        for (int x1 = 1; x1 <= n; x1++) {
            for (int x2 = x1+1; x2 <= n; x2++) {
                int xd = x1*(x2-x1);
                for (int y1 = (int) Math.sqrt(xd)+1; y1 <= n; y1++) {
                    if (xd % y1 != 0) continue;
                    int y2 = y1 - xd/y1;
                    if (y2*x1 >= (y1*x2)) continue;
                    count += 2;
                }
            }
        }
        return count;
    }
}
