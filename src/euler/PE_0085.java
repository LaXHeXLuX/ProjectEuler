package euler;

public class PE_0085 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long limit = 2_000_000L;
        return String.valueOf(rectangleClosestTo(limit));
    }

    private static long rectangleClosestTo(long L) {
        long closestDiff = Long.MAX_VALUE;
        long closestArea = -1;

        int nLimit = (int) Math.pow(L << 2, 0.25);
        for (int n = 1; n <= nLimit; n++) {
            int m = m(n, L << 2);
            long f = f(n, m);
            long diff = Math.abs(L - f);
            if (diff < closestDiff) {
                closestDiff = diff;
                closestArea = (long) n * m;
            }
        }

        return closestArea;
    }

    private static int m(int n, long L) {
        return (int) Math.sqrt((double) L / (n*(n+1)));
    }

    private static long f(int m, int n) {
        return ((long) n * m * (n+1) * (m+1)) >> 2;
    }
}
