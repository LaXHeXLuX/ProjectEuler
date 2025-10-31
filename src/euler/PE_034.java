package src.euler;

import util.Combinations;
import util.Converter;

import java.util.*;

public class PE_034 {
    static Map<Integer, Integer> factorials = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int[] digitFactorials = findAllDigitFactorials();
        int sum = 0;
        for (int digitFactorial : digitFactorials) sum += digitFactorial;
        return sum;
    }

    private static boolean isDigitFactorial(int n) {
        long sum = 0;

        int x = n;
        while (x > 0) {
            sum += factorials.get(x % 10);
            x /= 10;
        }

        return sum == n;
    }

    private static int[] findAllDigitFactorials() {
        List<Integer> digitFactorials = new ArrayList<>();
        int limit = 2_000_000;

        for (int i = 0; i < 10; i++) {
            factorials.put(i, (int) Combinations.factorial(i));
        }

        for (int i = 10; i < limit; i++) {
            if (isDigitFactorial(i)) digitFactorials.add(i);
        }

        return Converter.listToArr(digitFactorials);
    }
}
