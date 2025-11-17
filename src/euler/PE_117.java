package euler;

public class PE_117 {
    private static long[] waysForLength;

    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int[] sizes = {2, 3, 4};
        int length = 50;
        return waysForLength(length, sizes);
    }

    private static long waysForLength(int length, int[] sizes) {
        waysForLength = new long[length+1];
        return waysForLengthRec(length, sizes);
    }

    private static long waysForLengthRec(int length, int[] sizes) {
        if (length < sizes[0]) return 1;
        if (waysForLength[length] > 0) return waysForLength[length];

        long sum = waysForLengthRec(length - 1, sizes);
        for (int size : sizes) {
            if (size > length) break;
            sum += waysForLengthRec(length - size, sizes);
        }
        waysForLength[length] = sum;
        return sum;
    }
}
