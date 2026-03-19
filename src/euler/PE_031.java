package euler;

import java.util.Arrays;

public class PE_031 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long[] coins = {1, 2, 5, 10, 20, 50, 100};
        long goal = 200;
        return String.valueOf(waysToMake(goal, coins) + 1);
    }

    private static long waysToMake(long goal, long[] coins) {
        Arrays.sort(coins);
        return waysToMake(goal, coins, 0);
    }

    private static long waysToMake(long remaining, long[] coins, int index) {
        if (remaining < 0) return 0;
        if (remaining == 0) return 1;

        long sum = 0;
        for (int i = index; i < coins.length; i++) {
            sum += waysToMake(remaining-coins[i], coins, i);
        }
        return sum;
    }
}
