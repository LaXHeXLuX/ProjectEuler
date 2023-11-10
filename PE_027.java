import UsefulFunctions.SieveOfPrimes;

import java.util.Arrays;

public class PE_027 {
    public static void main(String[] args) {
        int limit = 50_000;
        int[] bestAB = findBestQuadraticFormulaWithLimits(limit, limit);
        System.out.println(Arrays.toString(bestAB));
        System.out.println(bestAB[0]*bestAB[1]);
    }

    private static int[] findBestQuadraticFormulaWithLimits(int limitA, int limitB) {
        SieveOfPrimes sop = new SieveOfPrimes();
        boolean[] primes = sop.sieveOfPrimes(100_000_000);

        int bestScore = 0;
        int[] bestAB = {0, 0};
        for (int a = -limitA; a <= limitA; a++) {
            for (int b = -limitB; b <= limitB ; b++) {
                int score = scoreOfQuadraticFormula(a, b, primes);
                if (score > bestScore) {
                    bestScore = score;
                    bestAB[0] = a;
                    bestAB[1] = b;
                }
            }
        }
        return bestAB;
    }

    private static int scoreOfQuadraticFormula(int a, int b, boolean[] primes) {
        int n = 0;
        while (true) {
            int value = n*n + a*n + b;
            if (!isPrime(primes, value)) break;
            n++;
        }
        return n;
    }

    private static boolean isPrime(boolean[] primes, int n) {
        if (n <= 1) return false;
        return primes[n];
    }


}
