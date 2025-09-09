import util.Converter;
import util.Divisors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_023 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 28123;
        long[] abundantNumbers = findAbundantNumbers(limit);
        int sum = 0;
        for (int i = 1; i < limit; i++) {
            if (!isSumOfTwoAbundantNumbers(i, abundantNumbers)) {
                sum += i;
            }
        }
        return sum;
    }

    private static boolean isAbundant(long n) {
        return n < Divisors.sumOfDivisors(n);
    }

    private static long[] findAbundantNumbers(long limit) {
        List<Long> abundantNumbers = new ArrayList<>();

        for (long i = 2; i < limit; i++) {
            if (isAbundant(i)) abundantNumbers.add(i);
        }

        return Converter.listToArr(abundantNumbers);
    }

    private static boolean isSumOfTwoAbundantNumbers(long n, long[] abundantNumbers) {
        for (long abundantNumber : abundantNumbers) {
            if (abundantNumber > n) break;
            long j = n - abundantNumber;
            if (Arrays.binarySearch(abundantNumbers, j) >= 0) return true;
        }

        return false;
    }
}
