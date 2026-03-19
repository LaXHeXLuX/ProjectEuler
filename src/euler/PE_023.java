package euler;

import utils.Converter;
import utils.Divisors;

import java.util.ArrayList;
import java.util.List;

public class PE_023 {
    private static final int limit = 28123;
    private static final boolean[] abundantNumber = new boolean[limit];
    private static int[] abundantNumbers;
    private static int[] oddAbundantNumbers;

    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        makeAbundantNumbers();
        int sum = sumOfAllNotSums();
        return String.valueOf(sum);
    }

    private static int sumOfAllNotSums() {
        int sum = 0;
        for (int i = 0; i < 50; i++) {
            if (!isSumOfTwoAbundantNumbers(i)) {
                sum += i;
            }
        }
        for (int i = 51; i < limit; i+=2) {
            if (!isSumOfTwoAbundantNumbersOdd(i)) {
                sum += i;
            }
        }
        return sum;
    }

    private static void makeAbundantNumbers() {
        int[] sumOfDivisors = Divisors.divisorSums(limit, false);
        List<Integer> abundantNumbersList = new ArrayList<>();
        List<Integer> oddAbundantNumbersList = new ArrayList<>();
        for (int i = 2; i < limit; i++) {
            if (i < sumOfDivisors[i]+1) {
                if (i % 2 == 1) {
                    oddAbundantNumbersList.add(i);
                }
                abundantNumbersList.add(i);
                abundantNumber[i] = true;
            }
        }
        oddAbundantNumbers = Converter.listToArr(oddAbundantNumbersList);
        abundantNumbers = Converter.listToArr(abundantNumbersList);
    }

    private static boolean isSumOfTwoAbundantNumbers(int n) {
        for (Integer i : abundantNumbers) {
            if (i > n) break;
            int j = n - i;
            if (abundantNumber[j]) return true;
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
