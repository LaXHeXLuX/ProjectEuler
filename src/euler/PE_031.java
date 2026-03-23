package euler;

import java.util.Arrays;

public class PE_031 {
    private static long[][] cache;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};
        int goal = 200;
        return String.valueOf(waysToMake(goal, coins));
    }

    private static long waysToMake(int goal, int[] coins) {
        Arrays.sort(coins);
        int index = Arrays.binarySearch(coins, goal);
        long ways = 0;
        if (index < 0) index = -index - 1;
        else ways++;
        int[] newCoins = Arrays.copyOf(coins, index);
        cache = new long[goal+1][newCoins.length];
        return ways + waysToMake(goal, newCoins, newCoins.length-1);
    }

    private static long waysToMake(int remaining, int[] coins, int index) {
        if (index == 0) {
            if (remaining % coins[0] == 0) return 1;
            return 0;
        }
        if (cache[remaining][index] > 0) return cache[remaining][index];
        long sum = 0;

        int temp = remaining;
        while (remaining >= 0) {
            sum += waysToMake(remaining, coins, index-1);
            remaining -= coins[index];
        }

        cache[temp][index] = sum;
        return sum;
    }
}
