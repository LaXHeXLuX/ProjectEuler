package euler;

public class PE_076 {
    private static long[][] waysToSum;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 100;
        waysToSum = new long[n+1][n+1];
        return String.valueOf(waysToSum(n));
    }

    private static long waysToSum(int n) {
        return waysToSum(n, 1);
    }

    private static long waysToSum(int n, int biggestAdder) {
        if (n <= 1 || n == biggestAdder) return 1;
        if (waysToSum[n][biggestAdder] > 0) return waysToSum[n][biggestAdder];

        long sum = 0;
        for (int i = biggestAdder; i < n; i++) {
            sum += waysToSum(n-i, i);
        }
        waysToSum[n][biggestAdder] = sum;
        return sum;
    }
}
