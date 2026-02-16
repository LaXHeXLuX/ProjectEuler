package euler;

import utils.Combinations;
import utils.Diophantine;
import utils.Fraction;

public class PE_205 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        // giving answer as abcdefg, not 0.abcdefg
        int pDice = 9;
        int pSides = 4;
        int cDice = 6;
        int cSides = 6;
        int digits = 7;
        Fraction<Long> peterWinChance = p2WinChance(cDice, cSides, pDice, pSides);
        return Math.round(peterWinChance.doubleValue() * Diophantine.pow(10, digits));
    }

    private static Fraction<Long> p2WinChance(int dice1, int sides1, int dice2, int sides2) {
        Fraction<Long> winChance = new Fraction<>(0L);
        for (int score = dice1; score <= dice1*sides1; score++) {
            Fraction<Long> winChanceForScore = scoreProbability(score, dice1, sides1).multiply(scoreProbabilityOver(score, dice2, sides2));
            winChance = winChance.add(winChanceForScore).simplify();
        }
        return winChance;
    }

    private static Fraction<Long> scoreProbabilityOver(int score, int dice, int sides) {
        Fraction<Long> probability = new Fraction<>(0L);
        for (int i = score+1; i <= dice*sides; i++) {
            probability = probability.add(scoreProbability(i, dice, sides));
        }
        return probability;
    }

    private static Fraction<Long> scoreProbability(int score, int dice, int sides) {
        long sum = 0;
        long den = Diophantine.pow(sides, dice);
        if (score < dice) return new Fraction<>(sum, den);
        for (int i = 0; i <= (score-dice) / sides; i++) {
            long product = Combinations.nChooseM(dice, i) * Combinations.nChooseM(score - sides*i - 1, dice - 1);
            if (i % 2 == 0) sum += product;
            else sum -= product;
        }
        return new Fraction<>(sum, den);
    }
}
