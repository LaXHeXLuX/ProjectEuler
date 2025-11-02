package euler;

import java.util.*;

public class PE_070 {
    private static int[] totients;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 10_000_000;
        makeTotients(limit);
        return findNumberWithPropertyWithSmallestScore();
    }

    private static void makeTotients(int limit) {
        totients = new int[limit];
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
        for (int i = limit/2; i < limit; i++) {
            if (totients[i] == 1) totients[i] = i-1;
        }
    }

    private static int compare(int[] fraction1, int[] fraction2) {
        long prod1 = (long) fraction1[0] * fraction2[1];
        long prod2 = (long) fraction2[0] * fraction1[1];
        return Long.compare(prod1, prod2);
    }

    private static int findNumberWithPropertyWithSmallestScore() {
        int[] smallestScore = {-1, 1};
        int smallestN = -1;
        for (int i = totients.length-1; i > 1; i-=2) {
            int[] score;
            if (totients[i] == i-1) {
                if (smallestScore[0] >= 0 && compare(smallestScore, new int[] {i, totients[i]}) < 0) {
                    return smallestN;
                }
                continue;
            }
            if (!hasProperty(i, totients[i])) continue;
            score = new int[] {i, totients[i]};
            if (smallestScore[0] < 0 || compare(score, smallestScore)< 0) {
                smallestN = i;
                smallestScore = score;
            }
        }

        return smallestN;
    }

    private static boolean hasProperty(long n, long totient) {
        Map<Integer, Integer> digits = new HashMap<>();
        while (n > 0) {
            int digit = Math.toIntExact(n % 10);
            digits.put(digit, digits.getOrDefault(digit, 0) + 1);
            n /= 10;
        }
        while (totient > 0) {
            int digit = Math.toIntExact(totient % 10);
            int current = digits.getOrDefault(digit, 0);
            if (current == 0) return  false;
            if (current == 1) digits.remove(digit);
            else digits.put(digit, current-1);

            totient /= 10;
        }

        return digits.isEmpty();
    }
}
