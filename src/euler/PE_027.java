package euler;

import utils.Primes;

public class PE_027 {
    private static boolean[] primes;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000;
        int[] bestAB = findBestQuadraticFormulaWithLimits(limit, limit);
        return (long) bestAB[0] * bestAB[1];
    }

    private static int[] findBestQuadraticFormulaWithLimits(int limitA, int limitB) {
        primes = Primes.sieveOfPrimes(10_000_000);

        int bestScore = 0;
        int[] bestAB = {0, 0};
        for (int a = -limitA; a <= limitA; a++) {
            for (int b = -limitB; b <= limitB ; b++) {
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
            if (!isPrime(value)) break;
            n++;
        }
        return n;
    }

    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        return primes[n];
    }
}
