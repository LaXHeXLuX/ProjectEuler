package euler;

import utils.Combinations;
import utils.Diophantine;
import utils.Primes;

import java.util.Arrays;

public class PE_070 {

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 10_000_000;
        return String.valueOf(numberWithPropertyWithSmallestScore2(limit));
    }

    private static double ratio(int limit, int[] initialBest) {
        double r = 0.5;
        long[] ratio = ratio(limit, r);
        while (initialBest[0] * ratio[1] >= ratio[0] * initialBest[1]) {
            r *= 1.01;
            ratio = ratio(limit, r);
        }
        return r;
    }

    private static long[] ratio(int limit, double r) {
        long p1 = (long) Math.pow(limit, r) - 1;
        long p2 = (long) Math.pow(limit, 1-r) - 1;
        return new long[] {limit, p1*p2};
    }

    private static int[] initialBestScore(int limit) {
        int[] totients = totients(limit);
        int[] minScore = {1, 0};
        for (int i = 10; i < limit; i++) {
            if (totients[i] == i-1) continue;
            if ((long) i * minScore[1] < (long) minScore[0] * totients[i]) {
                if (!Combinations.isPermutation(i, totients[i])) continue;
                minScore[0] = i;
                minScore[1] = totients[i];
            }
        }
        return minScore;
    }

    private static int numberWithPropertyWithSmallestScore2(int limit) {
        int initialLimit = 100_000;
        int[] initialBest = initialBestScore(initialLimit);
        double ratio = ratio(limit, initialBest);
        int[] primes = Primes.primes((int) Math.pow(limit, ratio));

        long minN = initialBest[0];
        long minTotient = initialBest[1];

        int p1High = Arrays.binarySearch(primes, (int) Math.sqrt(limit));
        if (p1High < 0) p1High = -p1High - 1;
        p1High--;
        int p1Low = Arrays.binarySearch(primes, (int) Math.pow(limit, 1-ratio));
        if (p1Low < 0) p1Low = -p1Low - 1;
        for (int p1i = p1High; p1i >= p1Low; p1i--) {
            int p1 = primes[p1i];
            int p2High = Arrays.binarySearch(primes, limit/p1);
            if (p2High < 0) p2High = -p2High - 2;
            for (int p2i = p1i+1; p2i < p2High; p2i++) {
                int p2 = primes[p2i];
                int n = p1*p2;
                int totient = (p1-1)*(p2-1);
                if (n*minTotient < minN*totient) {
                    if (!Combinations.isPermutation(n, totient)) continue;
                    minN = n;
                    minTotient = totient;
                }
            }
        }

        int[] minScoreWith3 = minScoreWithThree(limit);
        if (minN*minScoreWith3[1] > minScoreWith3[0]*minTotient) {
            return initialBestScore(limit)[0];
        }
        return (int) minN;
    }

    private static int[] minScoreWithThree(int limit) {
        int base = (int) Math.pow(limit, 1.0/3);
        return new int[] {limit, (base-1) * (int) Diophantine.pow((int) Math.pow(limit, 1.0/3), 2)};
    }

    private static int[] totients(int limit) {
        int[] totients = new int[limit];
        for (int i = 0; i < limit; i++) totients[i] = i;
        for (int i = 2; i < limit; i++) {
            if (totients[i] != i) continue;
            for (int j = i; j < limit; j += i) {
                totients[j] -= totients[j] / i;
            }
        }
        return totients;
    }
}
