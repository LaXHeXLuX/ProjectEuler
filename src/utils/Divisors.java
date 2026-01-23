package utils;

import java.util.ArrayList;
import java.util.List;

public class Divisors {
    public static long[] divisors(long n) {
        if (n < 1) throw new RuntimeException("Argument must be positive!");
        if (n == 1) return new long[] {1};
        List<Long> divisors = new ArrayList<>();
        divisors.add(1L);
        List<Long> biggerDivisors = new ArrayList<>();

        long limit = (long) Math.sqrt(n);
        for (long i = 2; i <= limit; i++) {
            if (n % i == 0) {
                divisors.add(i);
                long counterPart = n/i;
                if (counterPart != i) biggerDivisors.add(counterPart);
            }
        }

        for (int i = biggerDivisors.size()-1; i >= 0; i--) {
            divisors.add(biggerDivisors.get(i));
        }

        divisors.add(n);
        return Converter.listToArr(divisors);
    }
    public static int[] divisors(int n) {
        if (n < 1) throw new RuntimeException("Argument must be positive!");
        if (n == 1) return new int[] {1};
        List<Integer> divisors = new ArrayList<>();
        divisors.add(1);
        List<Integer> biggerDivisors = new ArrayList<>();

        int limit = (int) Math.sqrt(n);
        for (int i = 2; i <= limit; i++) {
            if (n % i == 0) {
                divisors.add(i);
                int counterPart = n/i;
                if (counterPart != i) biggerDivisors.add(counterPart);
            }
        }

        for (int i = biggerDivisors.size()-1; i >= 0; i--) {
            divisors.add(biggerDivisors.get(i));
        }

        divisors.add(n);
        return Converter.listToArr(divisors);
    }
    public static int sumOfDivisors(int n) {
        int[] divisors = divisors(n);
        int sum = 0;

        for (int i = 0; i < divisors.length-1; i++) {
            sum += divisors[i];
        }

        return sum;
    }
}
