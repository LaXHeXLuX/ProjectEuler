package euler;

import utils.Diophantine;

import java.util.ArrayList;
import java.util.List;

public class PE_030 {
    private static final long[] digitPowers = new long[10];
    private static List<Long> numbers;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int power = 5;
        makeDigitPowers(power);
        allNumbersWithSameSum();
        return String.valueOf(sum());
    }

    private static long sum() {
        long sum = 0;
        for (long i : numbers) sum += i;
        return sum;
    }

    private static void makeDigitPowers(int power) {
        for (int i = 0; i < 10; i++) {
            digitPowers[i] = Diophantine.pow(i, power);
        }
    }

    private static long sumOfNthPowersOfDigits(long number) {
        long sum = 0;
        while (number > 0) {
            sum += digitPowers[(int) (number % 10)];
            number /= 10;
        }
        return sum;
    }

    private static void allNumbersWithSameSum() {
        numbers = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            allNumbersWithSameSum(i, digitPowers[i], 1);
        }
    }

    private static void allNumbersWithSameSum(int biggest, long sum, int d) {
        if (sum > 1 && sumOfNthPowersOfDigits(sum) == sum) numbers.add(sum);
        if (sum + digitPowers[9] < Diophantine.pow10[d]) return;

        for (int i = biggest; i < 10; i++) {
            allNumbersWithSameSum(i, sum + digitPowers[i], d+1);
        }
    }
}
