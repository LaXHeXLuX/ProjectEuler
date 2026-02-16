package euler;

public class PE_114 {
    private static long[] waysForLength;

    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int length = 50;
        int minSize = 3;
        return waysForLength(length, minSize);
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
