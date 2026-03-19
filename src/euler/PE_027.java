package euler;

import utils.Primes;

public class PE_027 {
    private static boolean[] composites;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 1_000;
        int[] bestAB = bestQuadraticFormulaWithLimits(limit);
        return String.valueOf((long) bestAB[0] * bestAB[1]);
    }

    private static int[] bestQuadraticFormulaWithLimits(int limit) {
        int[] primes = Primes.primes(limit + 1);
        composites = Primes.compositeSieve(limit*100);

        int bestScore = 40;
        int[] bestAB = {1, 41};
        for (int b : primes) {
            int startA = -limit+1;
            if (startA % 2 == 0) startA++;
            for (int a = startA; a < limit; a+=2) {
                int score = scoreOfQuadraticFormula(a, b);
                if (score > bestScore) {
                    bestScore = score;
                    bestAB[0] = a;
                    bestAB[1] = b;
                }
            }
        }
        return bestAB;
    }

    private static int scoreOfQuadraticFormula(int a, int b) {
        int n = 0;
        while (true) {
            int value = n*n + a*n + b;
            if (value < 0 || composites[value >> 1]) break;
            n++;
        }
        return n;
    }
}
