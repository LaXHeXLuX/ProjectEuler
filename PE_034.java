import UsefulFunctions.Converter;
import UsefulFunctions.Factorial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_034 {
    public static void main(String[] args) {
        int[] digitFactorials = findAllDigitFactorials();
        System.out.println(Arrays.toString(digitFactorials));

        int sum = 0;
        for (int digitFactorial : digitFactorials) sum += digitFactorial;
        System.out.println(sum);
    }

    private static boolean isDigitFactorial(long n) {
        Converter c = new Converter();
        Factorial f = new Factorial();
        int[] digits = c.digitArray(n);
        long sum = 0;

        for (int digit : digits) {
            sum += f.factorialLong(digit);
        }

        return sum == n;
    }

    private static int[] findAllDigitFactorials() {
        List<Integer> digitFactorials = new ArrayList<>();
        int limit = 2540160;

        for (int i = 10; i < limit; i++) {
            if (isDigitFactorial(i)) digitFactorials.add(i);
        }

        Converter c = new Converter();
        return c.listToArrInt(digitFactorials);
    }
}
