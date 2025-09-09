import util.Converter;

import java.util.ArrayList;
import java.util.List;

public class PE_030 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int power = 5;
        long[] numbers = findAllNumbersWithSameSum(power);
        long sum = 0;
        for (long number : numbers) sum += number;
        return sum;
    }

    private static long sumOfNthPowersOfDigits(int n, long number) {
        int[] digits = Converter.digitArray(number);
        long sum = 0;
        for (int digit : digits) {
            sum += (long) Math.pow(digit, n);
        }
        return sum;
    }

    private static long[] findAllNumbersWithSameSum(int power) {
        if (power > 17) throw new RuntimeException("Power too large for long type");
        List<Long> numbers = new ArrayList<>();
        long limit = (long) Math.pow(9, power) * (power+1);
        for (long i = 10; i < limit; i++) {
            if (sumOfNthPowersOfDigits(power, i) == i) numbers.add(i);
        }

        return Converter.listToArr(numbers, Long.class);
    }
}
