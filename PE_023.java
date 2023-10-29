import UsefulFunctions.ArrayFunctions;
import UsefulFunctions.Converter;
import UsefulFunctions.Divisors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_023 {
    public static void main(String[] args) {
        int limit = 28123;
        long[] abundantNumbers = findAbundantNumbers(limit);
        System.out.println(Arrays.toString(abundantNumbers));
        int sum = 0;
        long start = System.currentTimeMillis();
        for (int i = 1; i < limit; i++) {
            if (!isSumOfTwoAbundantNumbers(i, abundantNumbers)) sum += i;
        }
        System.out.println(sum);
        System.out.println("AEG: " + (System.currentTimeMillis()-start));

    }
    public static boolean isAbundant(long n) {
        Divisors d = new Divisors();
        return n < d.sumOfDivisors(n);
    }
    public static long[] findAbundantNumbers(long limit) {
        List<Long> abundantNumbers = new ArrayList<>();
        for (long i = 2; i < limit; i++) {
            if (isAbundant(i)) abundantNumbers.add(i);
        }
        Converter c = new Converter();
        return c.listToArrLong(abundantNumbers);
    }
    public static boolean isSumOfTwoAbundantNumbers(long n, long[] abundantNumbers) {
        for (long abundantNumber : abundantNumbers) {
            if (abundantNumber > n) break;
            int j = (int) (n - abundantNumber);
            ArrayFunctions af = new ArrayFunctions();
            if (af.sortedContains(j, abundantNumbers)) return true;
        }
        return false;
    }
}
