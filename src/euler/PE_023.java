package euler;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class PE_023 {
    private static final Set<Integer> abundantNumberSet = new LinkedHashSet<>();
    private static final List<Integer> oddAbundantNumbers = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 28123;
        makeAbundantNumbers(limit);
        int sum = 0;
        for (int i = 1; i < limit; i++) {
            if (!isSumOfTwoAbundantNumbers(i)) {
                sum += i;
            }
        }
        return sum;
    }
    private static void makeAbundantNumbers(int limit) {
        int[] sumOfDivisors = new int[limit];
        int sqrt = (int) Math.sqrt(limit);
        for (int i = 2; i < sqrt; i++) {
            for (int j = 2*i; j < limit; j+=i) {
                sumOfDivisors[j] += i;
                int other = j / i;
                if (other >= sqrt) sumOfDivisors[j] += other;
            }
        }
        if (sqrt*sqrt < limit) sumOfDivisors[sqrt*sqrt] += sqrt;
        for (int i = 2; i < limit; i++) {
            if (i < sumOfDivisors[i]+1) {
                if (i % 2 == 1) {
                    oddAbundantNumbers.add(i);
                }
                abundantNumberSet.add(i);
            }
        }
    }

    private static boolean isSumOfTwoAbundantNumbers(int n) {
        if (n % 2 == 1) {
            if (n < oddAbundantNumbers.getFirst()) return false;
            for (Integer i : oddAbundantNumbers) {
                if (i > n) break;
                int j = n - i;
                if (abundantNumberSet.contains(j)) return true;
            }
        }
        for (int i : abundantNumberSet) {
            if (i > n) break;
            int j = n - i;
            if (abundantNumberSet.contains(j)) return true;
        }

        return false;
    }
}
