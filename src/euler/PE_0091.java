package euler;

import utils.Diophantine;

public class PE_0091 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int L = 50;
        return String.valueOf(rightTriangles(L));
    }

    private static long rightTriangles(int L) {
        long count = 3L*L*L;

        for (int y1 = 1; y1 <= L; y1++) {
            for (int y2 = 1; y2 < y1; y2++) {
                int x2 = Diophantine.root(y2*(y1-y2));
                if (x2 > 0) count += 2;
            }
        }

        for (int x1 = 1; x1 <= L; x1++) {
            for (int x2 = x1+1; x2 <= L; x2++) {
                int xd = x1*x1 - x1*x2;
                for (int y1 = 1; y1 <= L; y1++) {
                    if (xd % y1 != 0) continue;
                    int y2 = xd / y1 + y1;
                    if (y2 <= 0 || y2 > L) continue;
                    if (y1 == y2 && x1 == x2) continue;
                    count += 2;
                }
            }
        }

        return count;
    }
}
