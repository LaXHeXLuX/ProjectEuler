package src.euler;

import util.ArrayFunctions;
import util.Divisors;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PE_088 {
    private static final Map<Integer, long[]> allDivisors = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 12_000;
        Set<Integer> numbers = minimalProductSumNumbers(limit);
        return sum(numbers);
    }

    private static Set<Integer> minimalProductSumNumbers(int limit) {
        Set<Integer> minimalProductSumNumbers = new HashSet<>();

        for (int i = 2; i <= limit ; i++) {
            minimalProductSumNumbers.add(minimalProductSumNumber(i));
        }

        return minimalProductSumNumbers;
    }

    private static int minimalProductSumNumber(int k) {
        if (k == 2) return 4;
        for (int i = k+3; i < 2*k; i++) {
            if (isProductSum(k, i)) return i;
        }
        return 2*k;
    }

    private static boolean isProductSum(int k, int i) {
        long[] divisors;
        if (!allDivisors.containsKey(i)) {
            divisors = Divisors.divisors(i);
            divisors = ArrayFunctions.subArray(divisors, 1, divisors.length-2);
            allDivisors.put(i, divisors);
        } else {
            divisors = allDivisors.get(i);
        }

        return isProductSum(k, i, i, divisors, 0);
    }

    private static boolean isProductSum(int k, int sum, int product, long[] divisors, int start) {
        if (product < 1 || sum < 0) return false;
        if (product == 1) {
            return sum == k;
        }

        for (int i = start; i < divisors.length; i++) {
            if (product % divisors[i] != 0) continue;
            if (isProductSum(k - 1, (int) (sum - divisors[i]), (int) (product / divisors[i]), divisors, i))
                return true;
        }

        return false;
    }
    private static int sum(Set<Integer> set) {
        int sum = 0;
        for (Integer i : set) {
            sum += i;
        }
        return sum;
    }
}
