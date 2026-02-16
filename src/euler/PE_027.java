package euler;

import utils.Primes;

public class PE_027 {

    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000;
        int[] bestAB = bestQuadraticFormulaWithLimits(limit-1, limit);
        return (long) bestAB[0] * bestAB[1];
    }

    private static int[] bestQuadraticFormulaWithLimits(int limitA, int limitB) {
        boolean[] composites = Primes.compositeSieve(limitB + 1);

        int bestScore = 40;
        int[] bestAB = {1, 41};
        for (int b = bestAB[1]; b <= limitB; b+=2) {
            if (composites[b >> 1]) continue;
            for (int a = -limitA; a < limitA; a++) {
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
            if (!Primes.isPrime(value)) break;
            n++;
        }
        return n;
    }
}
