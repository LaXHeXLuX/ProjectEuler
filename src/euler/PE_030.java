package euler;

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
        long sum = 0;
        for (int number : numbers) sum += number;
        return String.valueOf(sum);
    }

    private static void makeDigitPowers(int power) {
        for (int i = 0; i < 10; i++) {
            digitPowers[i] = (int) Math.pow(i, power);
        }
    }

    private static long sumOfNthPowersOfDigits(int number) {
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
        int limit = (int) Math.pow(9, power) * (power+1);
        for (int i = 10; i < limit; i++) {
            if (sumOfNthPowersOfDigits(i) == i) numbers.add(i);
        }

        return numbers;
    }
}
