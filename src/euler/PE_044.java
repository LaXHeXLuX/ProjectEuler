package euler;

public class PE_044 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        return String.valueOf(smallestDifferenceWithProperty());
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
        for (int n2 = 2; ; n2++) {
            long p2 = pentagonal(n2);
            for (int n1 = n2-1; n1 > 0; n1--) {
                long p1 = pentagonal(n1);
                if (p1 >= smallestDiff && p2 >= smallestDiff) break;
                if (isPentagonal(p1 + p2)) {
                    if (isPentagonal(p1 + 2 * p2) && p1 < smallestDiff) smallestDiff = p1;
                    if (isPentagonal(2 * p1 + p2) && p2 < smallestDiff) smallestDiff = p2;
                }
            }
            if (3L*n2 - 2 > smallestDiff) return smallestDiff;
        }
    }
}
