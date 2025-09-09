import util.Primes;

public class PE_027 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000;
        int[] bestAB = findBestQuadraticFormulaWithLimits(limit, limit);
        return (long) bestAB[0] * bestAB[1];
    }

    private static int[] findBestQuadraticFormulaWithLimits(int limitA, int limitB) {
        boolean[] primes = Primes.sieveOfPrimes(100_000_000);

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
