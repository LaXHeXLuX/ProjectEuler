package euler;

import utils.Divisors;

import java.util.ArrayList;
import java.util.List;

public class PE_023 {
    private static final int limit = 28123;
    private static final boolean[] abundantNumber = new boolean[limit];
    private static final List<Integer> oddAbundantNumbers = new ArrayList<>();

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        makeAbundantNumbers();
        int sum = sumOfAllNotSums();
        return String.valueOf(sum);
    }

    private static int sumOfAllNotSums() {
        int sum = 0;
        int evenAbundantSumLimit = 46;
        for (int i = 0; i <= evenAbundantSumLimit; i+=2) {
            if (!isSumOfTwoAbundantNumbers(i)) {
                sum += i;
            }
        }
        for (int i = 1; i < limit; i+=2) {
            if (!isSumOfTwoAbundantNumbersOdd(i)) sum += i;
        }
        return sum;
    }

    private static void makeAbundantNumbers() {
        int[] sumOfDivisors = Divisors.divisorSums(limit);
        for (int i = 2; i < limit; i++) {
            if (i < sumOfDivisors[i]) {
                if (i % 2 == 1) {
                    oddAbundantNumbers.add(i);
                }
                abundantNumber[i] = true;
            }
        }
    }

    private static boolean isSumOfTwoAbundantNumbers(int n) {
        for (int i = 0; i <= n/2; i++) {
            if (abundantNumber[i] && abundantNumber[n-i]) return true;
        }
        return false;
    }

    private static boolean isSumOfTwoAbundantNumbersOdd(int n) {
        for (Integer i : oddAbundantNumbers) {
            if (i > n) break;
            int j = n - i;
            if (abundantNumber[j]) return true;
        }
        return false;
    }
}
