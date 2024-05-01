import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PE_076 {
    private static Map<String, Long> waysToSum;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        waysToSum = new HashMap<>();

        int n = 100;
        System.out.println(waysToSum(n));

        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end-start) + " ms");
    }

    private static long waysToSum(int n) {
        return waysToSum(n, 1);
    }

    private static long waysToSum(int n, int biggestAdder) {
        if (n <= 1 || n == biggestAdder) {
            return 1;
        }

        String input = Arrays.toString(new int[]{n, biggestAdder});
        if (waysToSum.containsKey(input)) {
            return waysToSum.get(input);
        }

        long sum = 0;

        for (int i = biggestAdder; i < n; i++) {
            sum += waysToSum(n-i, i);
        }

        waysToSum.put(input, sum);

        return sum;
    }
}
