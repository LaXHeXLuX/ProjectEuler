package euler;

import utils.Combinations;

import java.util.ArrayList;
import java.util.List;

public class PE_034 {
    private static List<Long> numbers;
    private static long[] powBase;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int base = 10;
        makePowBase(base);
        digitFactorials(base);
        return String.valueOf(sum());
    }

    private static long sum() {
        long sum = 0;
        for (Long l : numbers) sum += l;
        return sum;
    }

    private static void makePowBase(int base) {
        powBase = new long[(int) (Math.log(Long.MAX_VALUE)/Math.log(base))];
        powBase[0] = 1;
        for (int i = 1; i < powBase.length; i++) {
            powBase[i] = base * powBase[i-1];
        }
    }

    private static long digitFactorialSum(long n, int base) {
        long sum = 0;

        while (n > 0) {
            sum += Combinations.FACTORIAL[(int) (n % base)];
            n /= base;
        }

        return sum;
    }

    private static void digitFactorials(int base) {
        numbers = new ArrayList<>();
        digitFactorials(base, 1, 0, 0);
    }

    private static void digitFactorials(int base, int start, long sum, int d) {
        if (sum > 2 && digitFactorialSum(sum, base) == sum) {
            numbers.add(sum);
        }
        if (sum + Combinations.FACTORIAL[base-1] < powBase[d+1]) return;

        for (int i = start; i < base; i++) {
            digitFactorials(base, i, sum + Combinations.FACTORIAL[i], d+1);
        }
    }
}
