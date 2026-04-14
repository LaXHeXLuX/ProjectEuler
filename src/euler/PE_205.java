package euler;

import utils.Combinations;
import utils.Diophantine;

public class PE_205 {
    private static long[] scoreChance1;
    private static long[] scoreChanceOver;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int pDice = 9;
        int pSides = 4;
        int cDice = 6;
        int cSides = 6;
        int digits = 7;
        long[] peterWinChance = p2WinChance(cDice, cSides, pDice, pSides);
        return "0." + Math.round((double) peterWinChance[0] / peterWinChance[1] * Diophantine.pow10[digits]);
    }

    private static void makeScoreChance(int dice1, int sides1, int dice2, int sides2) {
        scoreChance1 = new long[sides1*dice1 + 1];
        for (int i = dice1; i < scoreChance1.length; i++) {
            scoreChance1[i]= scoreChance(i, dice1, sides1);
        }

        scoreChanceOver = new long[sides2*dice2 + 1];
        scoreChanceOver[scoreChanceOver.length-1] = 0;
        for (int i = scoreChanceOver.length-2; i >= 0; i--) {
            scoreChanceOver[i] = scoreChanceOver[i+1] + scoreChance(i+1, dice2, sides2);
        }
    }

    private static long scoreChance(int score, int dice, int sides) {
        if (score < dice) return 0;
        long sum = Combinations.nChooseM(score - 1, dice - 1);
        int sign = -1;
        int limit = (score - dice) / sides;
        for (int i = 1; i <= limit; i++) {
            long correction = Combinations.nChooseM(dice, i) * Combinations.nChooseM(score - sides*i - 1, dice - 1);
            sum += sign* correction;
            sign *= -1;
        }
        return sum;
    }

    private static long[] p2WinChance(int dice1, int sides1, int dice2, int sides2) {
        makeScoreChance(dice1, sides1, dice2, sides2);

        long winChance = 0;
        for (int score = dice1; score < scoreChance1.length; score++) {
            winChance += scoreChance1[score]*scoreChanceOver[score];
        }

        long scoreFactor1 = Diophantine.pow(sides1, dice1);
        long scoreFactor2 = Diophantine.pow(sides2, dice2);
        return new long[] {winChance, scoreFactor1 * scoreFactor2};
    }
}
