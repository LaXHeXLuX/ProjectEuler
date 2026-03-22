package euler;

import utils.Combinations;

import java.util.ArrayList;
import java.util.List;

public class PE_034 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int base = 10;
        List<Long> digitFactorials = allDigitFactorials(base);
        return String.valueOf(sum(digitFactorials));
    }

    private static long sum(List<Long> list) {
        long sum = 0;
        for (Long l : list) sum += l;
        return sum;
    }

    private static long limit(int base) {
        long powTen = base;
        int x = 1;
        while (Combinations.FACTORIAL[base-1]*x > powTen) {
            x++;
            powTen *= base;
        }
        long first = Combinations.FACTORIAL[base-1]*x;
        while (first >= base) {
            first /= base;
        }
        return Combinations.FACTORIAL[(int) first] + Combinations.FACTORIAL[base-1]*(x-1);
    }

    private static long digitFactorialSum(long n, int base) {
        long sum = 0;

        while (n > 0) {
            sum += Combinations.FACTORIAL[(int) (n % base)];
            n /= base;
        }

        return sum;
    }

    private static List<Long> allDigitFactorials(int base) {
        List<Long> digitFactorials = new ArrayList<>();
        long limit = limit(base);

        for (long i = 1; i < limit/base; i++) {
            long f = digitFactorialSum(i, base);
            for (int j = 0; j < base; j++) {
                long n = base*i + j;
                if (f + Combinations.FACTORIAL[j] == n) digitFactorials.add(n);
            }
        }

        return digitFactorials;
    }
}
