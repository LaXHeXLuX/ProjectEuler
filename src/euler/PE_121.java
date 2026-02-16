package euler;

import utils.Combinations;
import utils.Fraction;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class PE_121 {
    private static final Map<Integer, Map<Integer, Fraction<BigInteger>>> results = new HashMap<>();

    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int turns = 15;
        Fraction<BigInteger> winChance = winChance(turns);
        return winChance.den.divide(winChance.num).longValue();
    }

    private static Fraction<BigInteger> winChance(int turns) {
        for (int i = 1; i <= turns; i++) {
            results.put(i, new HashMap<>());
        }
        int winTarget = turns / 2 + 1;
        Fraction<BigInteger> winChance = new Fraction<>(BigInteger.ZERO);
        for (int i = winTarget; i <= turns; i++) {
            winChance = winChance.add(chanceToGetWinAmount(i, turns));
        }
        return winChance;
    }

    private static Fraction<BigInteger> chanceToGetWinAmount(int winAmount, int turns) {
        if (winAmount > turns) return new Fraction<>(BigInteger.ZERO);
        if (winAmount == turns) return new Fraction<>(BigInteger.ONE, Combinations.factorialBigInteger(turns+1));
        if (winAmount == 0) return new Fraction<>(BigInteger.ONE,  BigInteger.valueOf(turns+1));
        if (results.get(turns).containsKey(winAmount)) return results.get(turns).get(winAmount);

        BigInteger bigTurns = BigInteger.valueOf(turns);

        Fraction<BigInteger> sum = new Fraction<>(BigInteger.ZERO);
        sum = sum.add(chanceToGetWinAmount(winAmount-1, turns-1).multiply(new Fraction<>(BigInteger.ONE, bigTurns.add(BigInteger.ONE))));
        sum = sum.add(chanceToGetWinAmount(winAmount, turns-1).multiply(new Fraction<>(bigTurns, bigTurns.add(BigInteger.ONE))));
        results.get(turns).put(winAmount, sum);
        return sum;
    }
}
