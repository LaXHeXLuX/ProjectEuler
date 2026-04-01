package euler;

public class PE_100 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long lowerLimit = 1_000_000_000_000L;
        long[] result = firstArrangementAfter(lowerLimit);
        return String.valueOf(result[1]);
    }

    private static long[] firstArrangementAfter(long lowerLimit) {
        long aLimit = lowerLimit * 2 + 1;
        long[] ab = {1, 1};
        while (ab[0] <= aLimit) {
            ab = nextAB(ab);
        }

        long x = (1 + ab[0]) / 2;
        long y = (1 + ab[1]) / 2;
        return new long[] {x, y};
    }

    private static long[] nextAB(long[] ab) {
        return new long[] {3*ab[0] + 4*ab[1], 2*ab[0] + 3*ab[1]};
    }
}
