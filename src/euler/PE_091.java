package euler;

public class PE_091 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 50;
        return rightTrianglesBelow(n);
    }

    private static long rightTrianglesBelow(int n) {
        long count = 0;
        for (int x1 = 0; x1 <= n; x1++) {
            for (int y1 = 0; y1 <= n; y1++) {
                if (x1 == 0 && y1 == 0) continue;
                for (int x2 = 0; x2 <= x1; x2++) {
                    int y2Limit = n;
                    if (x1 == x2) y2Limit = y1;
                    for (int y2 = 0; y2 <= y2Limit; y2++) {
                        if (x2 == 0 && y2 == 0) continue;
                        if (x1 == x2 && y1 == y2) continue;
                        int[] OP = {x1, y1};
                        int[] PQ = {x2-x1, y2-y1};
                        int[] QO = {-x2, -y2};
                        if (
                            dot(OP, PQ) == 0 ||
                            dot(PQ, QO) == 0 ||
                            dot(QO, OP) == 0
                        ) count++;
                    }
                }
            }
        }
        return count;
    }

    private static int dot(int[] v1, int[] v2) {
        return v1[0] * v2[0] + v1[1] * v2[1];
    }
}
