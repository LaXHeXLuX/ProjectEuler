package euler;

public class PE_116 {
    private static long[] waysForLength;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int[] sizes = {2, 3, 4};
        int length = 50;
        return waysForLength(length, sizes);
    }

    private static long waysForLength(int length, int[] sizes) {
        long sum = 0;
        for (int size : sizes) {
            sum += waysForLength(length, size);
        }
        return sum;
    }

    private static long waysForLength(int length, int size) {
        waysForLength = new long[length+1];
        return waysForLengthRec(length, size) - 1;
    }

    private static long waysForLengthRec(int length, int size) {
        if (length < size) return 1;
        if (waysForLength[length] > 0) return waysForLength[length];

        long sum = waysForLengthRec(length - 1, size) + waysForLengthRec(length - size, size);
        waysForLength[length] = sum;
        return sum;
    }
}
