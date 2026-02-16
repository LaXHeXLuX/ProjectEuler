package euler;

import java.util.Arrays;

public class PE_179 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 10_000_000;
        return consecutiveEqualDivisors(limit);
    }

    private static int consecutiveEqualDivisors(int limit) {
        int count = 0;

        limit++;
        int[] divisorCounts = new int[limit];
        Arrays.fill(divisorCounts, 1);

        for (int i = 2; i < limit; i++) {
            if (divisorCounts[i-1] == divisorCounts[i]) count++;
            if (divisorCounts[i] != 1) continue;
            long p = i;
            int pow = 1;
            while (p < limit) {
                for (int n = Math.toIntExact(p); n < limit; n += (int) p) {
                    divisorCounts[n] /= pow;
                    divisorCounts[n] *= pow+1;
                }
                p *= i;
                pow++;
            }
        }

        return count;
    }
}
