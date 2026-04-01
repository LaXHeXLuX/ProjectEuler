package euler;

import utils.Diophantine;

import java.util.*;

public class PE_093 {
    private static int[] digits;
    private static Set<F> results;

    private record F(int n, int d) {}

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 4;
        digits = new int[n];
        int[][] result = largestConsecutiveScore(n);
        return String.valueOf(answerFromResult(result));
    }

    public static long answerFromResult(int[][] result) {
        int[] digits = result[1];
        long answer = 0;
        for (int digit : digits) {
            answer = answer * 10 + digit;
        }
        return answer;
    }

    private static int[][] largestConsecutiveScore(int digitCount) {
        if (digitCount == 0) {
            int[] d = Arrays.copyOf(digits, digits.length);
            return new int[][] {{consecutiveScore(digits)}, d};
        }
        int largest = 0;
        int[] largestDigits = {};
        int currentIndex = digits.length - digitCount;
        int last = 0;
        if (currentIndex > 0) last = digits[currentIndex - 1];
        for (int i = last+1; i <= 10-digitCount; i++) {
            digits[currentIndex] = i;
            int[][] result = largestConsecutiveScore(digitCount-1);
            int currentScore = result[0][0];
            if (currentScore > largest) {
                largest = currentScore;
                largestDigits = result[1];
            }
        }
        return new int[][] {{largest}, largestDigits};
    }

    private static int consecutiveScore(int[] digits) {
        List<Integer> results = results(digits);
        results.sort(Integer::compareTo);
        int startIndex = Collections.binarySearch(results, 1);
        if (startIndex < 0) return 0;
        int last = results.get(startIndex);
        int i = 1;
        while (startIndex+i < results.size() && last + 1 == results.get(startIndex+i)) {
            last = results.get(startIndex+i);
            i++;
        }
        return i;
    }

    private static List<Integer> results(int[] digits) {
        results = new HashSet<>();
        for (int i = 0; i < digits.length; i++) {
            for (int j = i+1; j < digits.length; j++) {
                int used = (1 << i) + (1 << j);
                int d1 = digits[i];
                int d2 = digits[j];
                results(digits, used, new F(d1+d2, 1), digits.length - 2);
                results(digits, used, new F(d1*d2, 1), digits.length - 2);
                results(digits, used, new F(d1-d2, 1), digits.length - 2);
                results(digits, used, new F(d2-d1, 1), digits.length - 2);
                results(digits, used, new F(d1, d2), digits.length - 2);
                results(digits, used, new F(d2, d1), digits.length - 2);
            }
        }

        List<Integer> finalResults = new ArrayList<>();
        for (F result : results) {
            if (result.d != 1) continue;
            finalResults.add(result.n);
        }
        finalResults.sort(Integer::compareTo);
        return finalResults;
    }

    private static void results(int[] digits, int used, F result, int depth) {
        if (depth == 0) {
            int gcd = Diophantine.gcd(result.n, result.d);
            results.add(new F(result.n/gcd, result.d/gcd));
            return;
        }

        for (int i = 0; i < digits.length; i++) {
            if ((used & (1 << i)) != 0) continue;
            int newUsed = used + (1 << i);
            results(digits, newUsed, new F(result.n + result.d*digits[i], result.d), depth-1);
            results(digits, newUsed, new F(result.n*digits[i], result.d), depth-1);
            results(digits, newUsed, new F(result.n - result.d*digits[i], result.d), depth-1);
            results(digits, newUsed, new F(result.d*digits[i] - result.n, result.d), depth-1);
            results(digits, newUsed, new F(result.n, result.d*digits[i]), depth-1);
            if (result.n > 0) {
                results(digits, newUsed, new F(result.d*digits[i], result.n), depth-1);
            }
        }
    }
}
