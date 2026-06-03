package euler;

import utils.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_0297 {
    private static long[] fib;
    private static long[] sumFib;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long limit = 100_000_000_000_000_000L;
        makeFib(limit);
        makeSumFib();
        return String.valueOf(sum(limit));
    }

    private static long sum(long n) {
        long result = 0;
        while (n > 3) {
            int i = Arrays.binarySearch(fib, n);
            if (i > 0) return sumFib[i];
            i = -i-2;
            result += sumFib[i] + n - fib[i];
            n -= fib[i];
        }
        result += n-1;
        return result;
    }

    private static void makeFib(long limit) {
        List<Long> fibonacci = new ArrayList<>();
        long x1 = 1;
        long x2 = 2;
        fibonacci.add(x1);
        while (x1 < limit) {
            fibonacci.add(x2);
            long temp = x1;
            x1 = x2;
            x2 = x2 + temp;
        }
        fibonacci.add(x2);
        fib = Converter.listToArr(fibonacci);
    }

    private static void makeSumFib() {
        sumFib = new long[fib.length];
        sumFib[0] = 0;
        sumFib[1] = 1;
        for (int i = 2; i < sumFib.length; i++) {
            sumFib[i] = fib[i-2] + sumFib[i-2] + sumFib[i-1];
        }
    }
}
