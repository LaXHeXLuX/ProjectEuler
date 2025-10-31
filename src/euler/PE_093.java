package euler;

import utils.Combinations;

import java.util.*;

public class PE_093 {
    private static int[] digits;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 4;
        digits = new int[n];
        int[][] result = largestConsecutiveScore(n);
        return answerFromResult(result);
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
        Set<Double> results = new HashSet<>();
        int[][] perms = Combinations.permutations(digits);
        for (int[] perm : perms) {
            List<Integer> permList = new ArrayList<>();
            for (int i : perm) permList.add(i);
            results.addAll(resultsRec(permList));
        }

        List<Integer> resultsInt = new ArrayList<>();
        for (Double d : results) {
            int i = (int) (double) d;
            if (i == d) resultsInt.add(i);
        }

        return resultsInt;
    }

    private static Set<Double> resultsRec(List<Integer> digits) {
        if (digits.size() == 1) {
            return Set.of((double) digits.getFirst());
        }
        Set<Double> results = new HashSet<>();
        for (int leftSize = 1; leftSize < digits.size(); leftSize++) {
            Set<Double> leftResults = resultsRec(digits.subList(0, leftSize));
            Set<Double> rightResults = resultsRec(digits.subList(leftSize, digits.size()));
            for (Double l : leftResults) {
                for (Double r : rightResults) {
                    results.add(l + r);
                    results.add(l - r);
                    results.add(l * r);
                    results.add(l / r);
                }
            }
        }
        return results;
    }
}
