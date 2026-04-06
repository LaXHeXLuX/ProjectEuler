package euler;

import utils.Combinations;
import utils.Fraction;

public class PE_121 {
    private static Fraction<Long>[][] results;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int turns = 15;
        Fraction<Long> winChance = winChance(turns);
        return String.valueOf(winChance.den / winChance.num);
    }

    @SuppressWarnings("unchecked")
    private static Fraction<Long> winChance(int turns) {
        results = new Fraction[turns+1][turns+1];
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
        if (results[turns][winAmount] != null) return results[turns][winAmount];

        Fraction<Long> sum = new Fraction<>(0L);
        sum = sum.add(chanceToGetWinAmount(winAmount-1, turns-1).multiply(new Fraction<>(1L, turns+1L)));
        sum = sum.add(chanceToGetWinAmount(winAmount, turns-1).multiply(new Fraction<>((long) turns, (long) turns+1)));
        results[turns][winAmount] = sum;
        return sum;
    }
}
