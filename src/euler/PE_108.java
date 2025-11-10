package euler;

import java.util.ArrayList;
import java.util.List;

public class PE_108 {
    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int limit = 1_000;
        return firstWithSolutionCountAbove(limit);
    }

    private static int firstWithSolutionCountAbove(int limit) {
        int n = (limit-1) / 2;
        while (solutionCount(n) <= limit) {
            n++;
        }
        return n;
    }

    private static int solutionCount(int n) {
        if (n <= 2) return n;
        List<List<Integer>> primeFactors = findPrimeFactors(n);
        int prod = 1;
        for (List<Integer> primeFactor : primeFactors) {
            prod *= 2*primeFactor.getLast() + 1;
        }
        return (prod+1)/2;
    }

    public static List<List<Integer>> findPrimeFactors(int n) {
        List<List<Integer>> primeFactors = new ArrayList<>();

        int count = 0;
        while (n % 2 == 0) {
            count++;
            n /= 2;
        }
        if (count > 0) {
            primeFactors.add(List.of(2, count));
            count = 0;
        }

        while (n % 3 == 0) {
            count++;
            n /= 3;
        }
        if (count > 0) {
            primeFactors.add(List.of(3, count));
            count = 0;
        }

        int limit = (int) Math.sqrt(n);
        for (int i = 5; i <= limit; i += 6) {
            while (n % i == 0) {
                count++;
                n /= i;
                limit = (int) Math.sqrt(n);
            }
            if (count > 0) {
                primeFactors.add(List.of(i, count));
                count = 0;
            }
            while (n % (i+2) == 0) {
                count++;
                n /= i+2;
                limit = (int) Math.sqrt(n);
            }
            if (count > 0) {
                primeFactors.add(List.of(i+2, count));
                count = 0;
            }
        }

        if (n > 1) primeFactors.add(List.of(n, 1));
        return primeFactors;
    }
}
