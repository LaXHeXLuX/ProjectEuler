package euler;

import utils.Combinations;

public class PE_0106 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 12;
        return String.valueOf(countOfSubsetPairs(n));
    }

    private static long countOfSubsetPairs(int n) {
        long sum = 0;
        for (int i = 1; i <= n/2; i++) {
            long binom1 = Combinations.nChooseM(n, 2*i);
            long binom2 = Combinations.nChooseM(2*i, i);
            long num = binom1 * binom2 * (i-1);
            long den = 2L*(i+1);
            sum += num / den;
        }
        return sum;
    }
}
