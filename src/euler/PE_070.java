package euler;

import utils.Combinations;

import java.util.*;

public class PE_070 {

    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int limit = 10_000_000;
        return findNumberWithPropertyWithSmallestScore(limit);
    }

    private static int[] totients(int limit) {
        int[] totients = new int[limit];
        Arrays.fill(totients, 1);
        for (int i = 2; i < limit/2; i++) {
            if (totients[i] > 1) continue;

            int prod = i;
            while (prod < limit) {
                totients[prod] *= i - 1;
                int n = prod / i;
                while (n % i == 0) {
                    totients[prod] *= i;
                    n /= i;
                }
                prod += i;
            }
        }
        return totients;
    }

    private static int findNumberWithPropertyWithSmallestScore(int limit) {
        double s = System.currentTimeMillis();
        int[] totients = totients(limit);
        double e = System.currentTimeMillis();
        System.out.println("totients done: " + (e-s) + " ms");
        int[] smallestScore = {Integer.MAX_VALUE, 1};
        int smallestN = -1;
        int lowerLimit = 2;
        for (int i = totients.length-1; i >= lowerLimit; i-=2) {
            int[] score;
            if (totients[i] == 1) continue;
            score = new int[] {i, totients[i]};
            if ((long) score[0] * smallestScore[1] >= (long) score[1] * smallestScore[0]) continue;
            if (!Combinations.isPermutation(i, totients[i])) continue;

            smallestN = i;
            smallestScore = score;
            long yy = (long) score[1] * score[1];
            long xMinusY = score[0] - score[1];
            lowerLimit = Math.toIntExact(yy / (xMinusY * xMinusY));
            System.out.println("new limit at " + i + ". score: " + Arrays.toString(score) + ", " + lowerLimit);
        }

        return smallestN;
    }
}
