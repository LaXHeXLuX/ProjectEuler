package euler;

import utils.Diophantine;

public class PE_091 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 50;
        return String.valueOf(rightTrianglesBelow2(n));
    }

    private static long rightTrianglesBelow2(int n) {
        long count = 3L*n*n;
        for (int x2 = 1; x2 <= n; x2++) {
            for (int x1 = 1; x1 < x2; x1++) {
                int y2 = Diophantine.root(x1*(x2-x1));
                if (y2 > 0) count += 2;
            }
        }
        for (int x1 = 1; x1 <= n; x1++) {
            for (int x2 = 1; x2 <= x1; x2++) {
                int xd = x2-x1;
                for (int y1 = 1; y1 <= n; y1++) {
                    int y2Limit = n;
                    if (x1 == x2) y2Limit = y1;
                    for (int y2 = 1; y2 <= y2Limit; y2++) {
                        if (x1 == x2 && y1 == y2) continue;
                        int yd = y2-y1;
                        if (
                            x1*xd + y1*yd == 0
                        ) count += 2;
                    }
                }
            }
        }
        return count;
    }
}
