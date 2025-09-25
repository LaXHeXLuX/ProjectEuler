import util.Combinations;

import java.util.*;

public class PE_093 {
    private static int[] digits;
    private static final int[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
    private static final int[] powersOf4 = {1, 4, 16, 64, 256, 1024, 4096, 16384, 65536, 262144};

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
        Set<Integer> resultsSet = new HashSet<>();
        int[][] permutations = Combinations.permutations(digits);
        for (int[] permutation : permutations) {
            for (int parenthesis = 0; parenthesis < factorials[digits.length-1]; parenthesis++) {
                for (int ops = 0; ops < powersOf4[digits.length-1]; ops++) {
                    int result;
                    try {
                        result = compute(permutation, parenthesis, ops);
                    } catch (ArithmeticException _) {
                        continue;
                    }
                    resultsSet.add(result);
                }
            }
        }

        List<Integer> results = new ArrayList<>(resultsSet);
        results.sort(Integer::compareTo);
        return results;
    }

    private static int compute(int[] permutation, int parenthesis, int ops) {
        List<Double> results = new ArrayList<>(Arrays.stream(permutation).asDoubleStream().boxed().toList());
        for (int i = permutation.length-1; i > 0; i--) {
            int currentPos = parenthesis % i;
            parenthesis /= i;
            int currentOp = ops % 4;
            ops /= 4;
            double i1 = results.get(currentPos);
            double i2 = results.get(currentPos+1);
            double result = switch (currentOp) {
                case 0 -> i1 + i2;
                case 1 -> i1 - i2;
                case 2 -> i1 * i2;
                case 3 -> i1 / i2;
                default -> throw new IllegalStateException("Unexpected value: " + currentOp);
            };
            results.set(currentPos, result);
            results.remove(currentPos+1);
        }
        int resultInt = (int) (double) results.getFirst();
        if ((double) resultInt != results.getFirst()) throw new ArithmeticException("Result not integer: " + results.getFirst());
        return resultInt;
    }
}
