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
                if (Diophantine.root((long) y2 * (y1-y2)) > 0) count += 2;
            }
        }

        for (int x1 = 1; x1 <= L; x1++) {
            for (int x2 = 1; x2 < x1; x2++) {
                long xd = (long) x1 * (x1 - x2);
                double sqrt = Math.sqrt((long) L * L - 4L*x1*(x1-x2));
                int low = (int) Math.ceil((L - sqrt)/2);
                if (low < 1) low = 1;
                int high = (int) Math.floor((L + sqrt)/2);
                for (int y1 = low; y1 <= high; y1++) {
                    if (xd % y1 != 0) continue;
                    count += 2;
                }
            }
        }

        return count;
    }
}
