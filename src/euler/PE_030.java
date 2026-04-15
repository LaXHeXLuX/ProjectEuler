package euler;

import utils.Diophantine;

import java.util.ArrayList;
import java.util.List;

public class PE_030 {
    private static final int[] digitPowers = new int[10];

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int power = 5;
        makeDigitPowers(power);
        List<Integer> numbers = allNumbersWithSameSum(power);
        return String.valueOf(sum(numbers));
    }

    private static int sum(List<Integer> list) {
        int sum = 0;
        for (Integer i : list) sum += i;
        return sum;
    }

    private static void makeDigitPowers(int power) {
        for (int i = 0; i < 10; i++) {
            digitPowers[i] = (int) Diophantine.pow(i, power);
        }
    }

    private static int sumOfNthPowersOfDigits(int number) {
        int sum = 0;
        while (number > 0) {
            sum += digitPowers[number % 10];
            number /= 10;
        }
        return sum;
    }

    private static List<Integer> allNumbersWithSameSum(int power) {
        if (power > 9) throw new RuntimeException("Power too large for int type");
        List<Integer> numbers = new ArrayList<>();
        int dLimit = 1;
        while ((int) Diophantine.pow10[dLimit] <= digitPowers[9] * dLimit) dLimit++;
        dLimit--;
        int limit = digitPowers[9] * dLimit;
        for (int i = 10; i < limit; i++) {
            if (sumOfNthPowersOfDigits(i) == i) numbers.add(i);
        }

        return numbers;
    }
}
