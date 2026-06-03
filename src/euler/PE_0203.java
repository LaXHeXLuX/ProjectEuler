package euler;

import java.util.HashSet;
import java.util.Set;

public class PE_0203 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int rows = 51;
        return String.valueOf(sumOfSquareFreeDistinctPascalNumbers(rows));
    }

    private static boolean squareFree(long n) {
        if (n % 4 == 0) return false;
        if (n % 2 == 0) n /= 2;
        long limit = (long) Math.sqrt(n);
        for (long i = 3; i <= limit; i+=2) {
            if (n % i == 0) {
                if (n % (i*i) == 0) return false;
                n /= i;
                limit = (long) Math.sqrt(n);
            }
        }
        return true;
    }

    private static long sumOfSquareFreeDistinctPascalNumbers(int rows) {
        long sum = 1;

        Set<Long> seen = new HashSet<>();
        seen.add(1L);
        long[] last = {1, 1};
        for (int i = 2; i < rows; i++) {
            long[] current = new long[i+1];
            current[0] = 1;
            current[i] = 1;
            for (int j = 1; j < i; j++) {
                current[j] = last[j-1] + last[j];
                if (seen.add(current[j]) && squareFree(current[j])) {
                    sum += current[j];
                }
            }
            last = current;
        }
        return sum;
    }
}
