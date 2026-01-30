package utils;

import java.util.ArrayList;
import java.util.List;

public class Divisors {
    private static long pow(long base, int exp) {
        if (exp == 0) return 1;
        long result = pow(base, exp/2);
        result = result*result;
        if (exp % 2 == 1) result *= base;
        return result;
    }
    public static long[] divisors(long n) {
        return divisors(n, 1);
    }
    public static long[] divisors(long n, int power) {
        if (n < 1 || power < 0) throw new IllegalArgumentException("n must be positive and power must be non-negative!");
        if (n == 1 || power == 0) return new long[] {1};

        int two = 0;
        while (n % 2 == 0) {
            two++;
            n /= 2;
        }
        int three = 0;
        while (n % 3 == 0) {
            three++;
            n /= 3;
        }

        List<Long> divisorsRec = divisorsRec(n, 5, power);
        List<Long> divisors = new ArrayList<>();
        for (int i2 = 0; i2 <= two*power; i2++) {
            long pow2 = pow(2, i2);
            for (int i3 = 0; i3 <= three*power; i3++) {
                long pow3 = pow(3, i3);
                long p = pow2*pow3;
                divisors.add(p);
                for (Long l : divisorsRec) {
                    divisors.add(l * p);
                }
            }
        }
        divisors.sort(Long::compare);
        return Converter.listToArr(divisors);
    }
    private static List<Long> divisorsRec(long n, long start, int power) {
        if (n < 2) return List.of();
        List<Long> divisors = new ArrayList<>();

        long limit = (long) Math.sqrt(n);
        long i;
        boolean found = false;
        for (i = start; i <= limit; i += 6) {
            if (n % i == 0) {
                found = true;
                break;
            }
            if (n % (i+2) == 0) {
                found = true;
                break;
            }
        }

        if (!found) {
            for (int p = 1; p <= power; p++) {
                divisors.add(pow(n, p));
            }
            return divisors;
        }

        int exp1 = 0;
        while (n % i == 0) {
            exp1++;
            n /= i;
        }
        int exp2 = 0;
        while (n % (i+2) == 0) {
            exp2++;
            n /= i+2;
        }

        List<Long> divisorsRec = divisorsRec(n, i+6, power);

        for (int e1 = 0; e1 <= exp1*power; e1++) {
            long p1 = pow(i, e1);
            for (int e2 = 0; e2 <= exp2*power; e2++) {
                long p2 = pow(i+2, e2);
                long p = p1*p2;
                if (p > 1) divisors.add(p);
                for (Long l : divisorsRec) {
                    divisors.add(l * p);
                }
            }
        }

        return divisors;
    }
    public static int[] divisors(int n) {
        if (n < 1) throw new IllegalArgumentException("Argument must be positive!");
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
    public static long sumOfDivisors(long n) {
        long[] divisors = divisors(n);
        long sum = 0;
        for (int i = 0; i < divisors.length-1; i++) {
            sum += divisors[i];
        }
        return sum;
    }
}
