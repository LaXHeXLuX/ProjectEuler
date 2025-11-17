package euler;

public class PE_115 {
    private static long[] waysForLength;

    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        long lengthToExceed = 1_000_000;
        int minSize = 50;
        return firstLengthToExceed(lengthToExceed, minSize);
    }

    private static int firstLengthToExceed(long n, int minSize) {
        int length = 1;
        long ways = waysForLength(length, minSize);
        while (ways <= n) {
            length++;
            ways = waysForLength(length, minSize);
        }
        return length;
    }

    private static long waysForLength(int n, int minSize) {
        waysForLength = new long[n+1];
        return waysForLengthRec(n, minSize);
    }

    private static long waysForLengthRec(int n, int minSize) {
        if (n < minSize) return 1;
        if (waysForLength[n] > 0) return waysForLength[n];
        long sum = 1;

        sum += waysForLengthRec(n-1, minSize);

        for (int i = minSize; i < n; i++) {
            sum += waysForLengthRec(n-i-1, minSize);
        }

        waysForLength[n] = sum;
        return sum;
    }
}
