package euler;

import utils.Combinations;
import utils.Fraction;

import java.util.HashMap;
import java.util.Map;

public class PE_121 {
    private static final Map<Integer, Map<Integer, Fraction<Long>>> results = new HashMap<>();

    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }

    public static String PE() {
        int turns = 15;
        Fraction<Long> winChance = winChance(turns);
        return String.valueOf(winChance.den / winChance.num);
    }

    private static Fraction<Long> winChance(int turns) {
        for (int i = 1; i <= turns; i++) {
            results.put(i, new HashMap<>());
        }
        int winTarget = turns / 2 + 1;
        Fraction<Long> winChance = new Fraction<>(0L);
        for (int i = winTarget; i <= turns; i++) {
            winChance = winChance.add(chanceToGetWinAmount(i, turns));
        }
        return winChance;
    }

    private static Fraction<Long> chanceToGetWinAmount(int winAmount, int turns) {
        if (winAmount > turns) return new Fraction<>(0L);
        if (winAmount == turns) return new Fraction<>(1L, Combinations.FACTORIAL[turns+1]);
        if (winAmount == 0) return new Fraction<>(1L, turns+1L);
        if (results.get(turns).containsKey(winAmount)) return results.get(turns).get(winAmount);

        Fraction<Long> sum = new Fraction<>(0L);
        sum = sum.add(chanceToGetWinAmount(winAmount-1, turns-1).multiply(new Fraction<>(1L, turns+1L)));
        sum = sum.add(chanceToGetWinAmount(winAmount, turns-1).multiply(new Fraction<>((long) turns, (long) turns+1)));
        results.get(turns).put(winAmount, sum);
        return sum;
    }
}
