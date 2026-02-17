package euler;

import utils.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_297 {
    private static long[] sumOfZFib;
    private static long[] fib;

    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        long limit = 100_000_000_000_000_000L;
        makeFibonacci(limit);
        return sumOfZ(limit);
    }

    private static long sumOfZFib(int n) {
        if (n < 3) return n;
        if (sumOfZFib[n] != 0) return sumOfZFib[n];
        long result = sumOfZFib(n-1) + sumOfZFib(n-2) + fib[n-2];
        sumOfZFib[n] = result;
        return result;
    }

    private static long sumOfZ(long limit) {
        if (limit <= 4) {
            if (limit == 0) return 0;
            return limit-1;
        }
        int i = Arrays.binarySearch(fib, limit);
        if (i > 0) return sumOfZFib(i);
        i = -i-2;
        return sumOfZFib(i) + sumOfZ(fib[i], limit);
    }

    private static long sumOfZ(long start, long limit) {
        return sumOfZ(limit - start) + limit - start;
    }

    private static void makeFibonacci(long limit) {
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
        sumOfZFib = new long[fib.length];
    }
}
