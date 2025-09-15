import java.util.ArrayList;
import java.util.List;

public class PE_030 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int power = 5;
        List<Integer> numbers = findAllNumbersWithSameSum(power);
        long sum = 0;
        for (int number : numbers) sum += number;
        return sum;
    }

    private static long sumOfNthPowersOfDigits(int power, int number) {
        int sum = 0;
        while (number > 0) {
            sum += (int) Math.pow(number % 10, power);
            number /= 10;
        }
        return sum;
    }

    private static List<Integer> findAllNumbersWithSameSum(int power) {
        if (power > 9) throw new RuntimeException("Power too large for int type");
        List<Integer> numbers = new ArrayList<>();
        int limit = (int) Math.pow(9, power) * (power+1);
        for (int i = 10; i < limit; i++) {
            if (sumOfNthPowersOfDigits(power, i) == i) numbers.add(i);
        }

        return numbers;
    }
}
