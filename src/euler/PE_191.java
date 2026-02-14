package euler;

import java.util.HashMap;
import java.util.Map;

public class PE_191 {
    private static final Map<Integer, Long> memoization = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int days = 30;
        return countPrizes(days);
    }

    private static long countPrizes(int days) {
        long sum = countPrizesHelper(days);
        for (int i = 0; i < days/2; i++) {
            sum += 2*countPrizesHelper(i)*countPrizesHelper(days - i - 1);
        }
        if (days % 2 == 1) {
            long s = countPrizesHelper(days/2);
            sum += s*s;
        }
        return sum;
    }

    private static long countPrizesHelper(int days) {
        if (days <= 2) return days + days/2 + 1;
        if (memoization.containsKey(days)) return memoization.get(days);
        long sum = countPrizesHelper(days-1);
        sum += countPrizesHelper(days-2);
        sum += countPrizesHelper(days-3);
        memoization.put(days, sum);
        return sum;
    }
}
