package euler;

import java.util.LinkedHashSet;
import java.util.Set;

public class PE_023 {
    private static final Set<Integer> abundantNumberSet = new LinkedHashSet<>();

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
            if (i < sumOfDivisors[i]+1) abundantNumberSet.add(i);
        }
    }

    private static boolean isSumOfTwoAbundantNumbers(int n) {
        for (int abundantNumber : abundantNumberSet) {
            if (abundantNumber > n) break;
            int j = n - abundantNumber;
            if (abundantNumberSet.contains(j)) return true;
        }

        return false;
    }
}
