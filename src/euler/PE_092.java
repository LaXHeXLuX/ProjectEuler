package euler;

import java.util.Arrays;

public class PE_092 {
    private static boolean[] chainEnds1;
    private static int[][] cache;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int digits = 7;
        return String.valueOf(countOf89Enders2(digits));
    }

    private static void makeChains(int lowerLimit) {
        chainEnds1 = new boolean[lowerLimit+1];
        for (int i = 1; i <= lowerLimit; i++) {
            int temp = i;
            while (temp != 1 && temp != 89) {
                temp = digitSquareSum(temp);
            }
            chainEnds1[i] = temp == 1;
        }
    }

    private static int countOf89Enders2(int digits) {
        int lowerLimit = digits*9*9;
        makeChains(lowerLimit);
        cache = new int[lowerLimit+1][digits+1];
        for (int[] c : cache) {
            Arrays.fill(c, -1);
        }
        int count = 0;
        for (int n = 1; n <= lowerLimit; n++) {
            if (chainEnds1[n]) continue;
            count += f(n, digits);
        }
        return count;
    }

    private static int f(int n, int k) {
        if (n == 0 && k == 0) return 1;
        if (n < 0 || k == 0) return 0;
        if (cache[n][k] >= 0) return cache[n][k];
        int count = 0;
        for (int i = 0; i < 10; i++) {
            count += f(n - i*i, k-1);
        }
        cache[n][k] = count;
        return count;
    }

    private static int digitSquareSum(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
}
