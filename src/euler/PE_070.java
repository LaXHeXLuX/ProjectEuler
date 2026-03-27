package euler;

import utils.Combinations;

public class PE_070 {

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 10_000_000;
        return String.valueOf(numberWithPropertyWithSmallestScore(limit));
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

    private static int numberWithPropertyWithSmallestScore(int limit) {
        int[] totients = totients(limit);
        int smallestScore = 1;
        int smallestN = Integer.MAX_VALUE;
        int lowerLimit = 2;
        for (int i = totients.length-1; i >= lowerLimit; i-=2) {
            if (totients[i] == 1) continue;
            int score = totients[i];
            if ((long) i * smallestScore >= (long) score * smallestN) continue;
            if (!Combinations.isPermutation(i, totients[i])) continue;

            smallestN = i;
            smallestScore = score;
            long yy = (long) score * score;
            long xMinusY = i - score;
            lowerLimit = Math.toIntExact(yy / (xMinusY * xMinusY));
        }

        return smallestN;
    }
}
