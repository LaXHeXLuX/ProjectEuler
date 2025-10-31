package euler;

import utils.Divisors;

public class PE_073 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int[] fraction1 = {1, 3};
        int[] fraction2 = {1, 2};
        int limit = 12_000;
        return numberOfFractionsBetween(fraction1, fraction2, limit);
    }

    private static long numberOfFractionsBetween(int[] fraction1, int[] fraction2, int limit) {
        long count = 0;
        for (int i = 2; i <= limit; i++) {
            int start = i * fraction1[0] / fraction1[1] + 1;
            int end = (i-1) * fraction2[0] / fraction2[1];
            for (int j = start; j <= end; j++) {
                if (Divisors.greatestCommonDivisor(i, j) == 1) count++;
            }
        }
        return count;
    }
}
