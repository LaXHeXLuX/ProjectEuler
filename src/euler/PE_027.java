package euler;

import utils.Primes;

public class PE_027 {

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000;
        int[] bestAB = findBestQuadraticFormulaWithLimits(limit-1, limit);
        return (long) bestAB[0] * bestAB[1];
    }

    private static int[] findBestQuadraticFormulaWithLimits(int limitA, int limitB) {
        boolean[] primes = Primes.sieveOfPrimes(limitB + 1);

        int bestScore = 40;
        int[] bestAB = {1, 41};
        for (int b = bestAB[1]; b <= limitB; b+=2) {
            if (!primes[b]) continue;
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
