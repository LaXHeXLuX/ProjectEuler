package euler;

public class PE_044 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        return smallestDifferenceWithProperty();
    }

    private static long pentagonal(long n) {
        return n*(3*n - 1) / 2;
    }

    private static boolean isPentagonal(long x) {
        long n = (long) ((Math.sqrt(24 * x + 1) + 1) / 6);
        return pentagonal(n) == x;
    }

    private static long smallestDifferenceWithProperty() {
        long smallestDiff = Long.MAX_VALUE;
        for (long j = 2; ; j++) {
            long pj = pentagonal(j);
            for (long k = j - 1; k > 0; k--) {
                long pk = pentagonal(k);
                long diff = pj - pk;
                if (diff >= smallestDiff) break;
                if (isPentagonal(diff) && isPentagonal(pj + pk)) {
                    smallestDiff = diff;
                }
            }
            if (smallestDiff < 3*j - 2) return smallestDiff;
        }
    }
}
