package src.euler;

import util.LongFraction;

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

    private static int findNumberWithPropertyWithSmallestScore() {
        LongFraction smallestScore = new LongFraction(-1, 1);
        int smallestN = -1;
        for (int i = totients.length-1; i > 1; i-=2) {
            LongFraction score;
            if (totients[i] == i-1) {
                if (smallestScore.numerator >= 0 && smallestScore.compareTo(new LongFraction(i, totients[i])) < 0) {
                    return smallestN;
                }
                continue;
            }
            if (!hasProperty(i, totients[i])) continue;
            score = new LongFraction(i, totients[i]);
            if (smallestScore.numerator < 0 || score.compareTo(smallestScore) < 0) {
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
