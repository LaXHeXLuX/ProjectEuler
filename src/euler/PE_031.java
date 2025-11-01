package euler;

import utils.ArrayFunctions;

public class PE_031 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        long[] coins = {1, 2, 5, 10, 20, 50, 100};
        long goal = 200;
        return waysToMake(goal, coins) + 1; // + 1 to account for the 200 coin
    }

    private static long waysToMake(long goal, long[] coins) {
        coins = ArrayFunctions.mergeSort(coins);
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
