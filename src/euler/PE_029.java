package euler;

import utils.Diophantine;

import java.util.Arrays;

public class PE_029 {

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limitA = 100;
        int limitB = 100;
        return String.valueOf(distinctPowerCount0(limitA, limitB));
    }

    private static long includeExclude(int p, int limitB) {
        long sum = 0;
        int sign = 1;
        for (int i = 1; i <= p; i++) {
            sum += sign * includeExclude(i, p, limitB);
            sign *= -1;
        }
        return sum;
    }

    private static long includeExclude(int count, int p, int limitB) {
        long sum = 0;
        for (int i = 1; i <= p - count + 1; i++) {
            sum += includeExclude(i, i, count-1, i, p, limitB);
        }
        return sum;
    }

    private static long includeExclude(int min, int max, int i, int lcm, int p, int limitB) {
        if (i == 0) return intersect(min, max, lcm, limitB);
        if (lcm > p*limitB) return 0;

        long sum = 0;
        for (int j = max+1; j <= p - i + 1; j++) {
            sum += includeExclude(min, j, i-1, Diophantine.lcm(lcm, j), p, limitB);
        }

        return sum;
    }

    private static int intersect(int min, int max, int lcm, int limitB) {
        return (int) ((long) min * limitB / lcm) - (max*2-1) / lcm;
    }

    private static long distinctPowerCount0(int limitA, int limitB) {
        long[] counts = new long[32 - Integer.numberOfLeadingZeros(limitA)];
        Arrays.fill(counts, -1);
        int sqrt = (int) Math.sqrt(limitA);

        long count = 0;
        boolean[] skip = new boolean[sqrt+1];
        int highSkip = 0;
        for (int a0 = 2; a0 <= sqrt; a0++) {
            if (skip[a0]) continue;
            long a = (long) a0 * a0;
            int k = 1;
            while (a <= limitA) {
                if (a <= sqrt) skip[(int) a] = true;
                else highSkip++;
                a *= a0;
                k++;
            }
            if (counts[k] == -1) counts[k] = includeExclude(k, limitB);
            count += counts[k];
        }
        count += (limitA - sqrt - highSkip) * (limitB - 1L);
        return count;
    }
}
