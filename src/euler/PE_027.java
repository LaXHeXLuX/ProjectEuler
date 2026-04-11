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
        int[] primes = Primes.primes(2*limit + 1);
        composites = Primes.compositeSieve(2*limit*limit);

        int bestScore = 40;
        int[] bestAB = {1, 41};
        for (int b : primes) {
            if (b > limit) break;
            for (int prime : primes) {
                int a = prime - b - 1;
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
