import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_030 {
    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            long[] numbers = findAllNumbersWithSameSum(i);
            long sum = 0;
            for (long number : numbers) sum += number;
            System.out.println(i + ": " + Arrays.toString(numbers) + ", " + sum);
        }
    }

    private static long sumOfNthPowersOfDigits(int n, long number) {
        int[] digits = Converter.digitArray(number);
        long sum = 0;
        for (int digit : digits) {
            sum += Math.pow(digit, n);
        }
        return sum;
    }

    private static long[] findAllNumbersWithSameSum(int power) {
        if (power > 17) throw new RuntimeException("Power too large for long type");
        List<Long> numbers = new ArrayList<>();
        long limit = (long) Math.pow(10, power+1);
        for (long i = 10; i < limit; i++) {
            if (sumOfNthPowersOfDigits(power, i) == i) numbers.add(i);
        }

        return Converter.listToArr(numbers);
    }
}
