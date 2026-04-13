package euler;

import java.util.HashSet;
import java.util.Set;

public class PE_203 {
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

        long[][] triangle = new long[rows][];
        triangle[0] = new long[] {1};
        Set<Long> distinctPascalNumbers = new HashSet<>();
        distinctPascalNumbers.add(1L);
        triangle[1] = new long[] {1, 1};
        for (int i = 2; i < rows; i++) {
            triangle[i] = new long[i/2 + 1];
            triangle[i][0] = 1;
            int l0 = triangle[i-1].length-1;
            int l1 = triangle[i].length-1;
            for (int j = 1; j < l1; j++) {
                triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j];
                if (distinctPascalNumbers.add(triangle[i][j]) && squareFree(triangle[i][j])) {
                    sum += triangle[i][j];
                }
            }
            if (i % 2 == 0) triangle[i][l1] = 2*triangle[i-1][l0];
            else triangle[i][l1] = triangle[i-1][l0-1] + triangle[i-1][l0];
            if (!distinctPascalNumbers.contains(triangle[i][l1]) && squareFree(triangle[i][l1])) {
                distinctPascalNumbers.add(triangle[i][l1]);
                sum += triangle[i][l1];
            }
        }
        return sum;
    }
}
