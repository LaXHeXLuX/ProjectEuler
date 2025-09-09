import util.Converter;

import java.util.ArrayList;
import java.util.List;

public class PE_002 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        long n = 4_000_000L;
        return sumOfEvenElements(fibonacciNumbersUntil(n));
    }

    private static long sumOfEvenElements(long[] arr) {
        long sum = 0;
        for (long element : arr) {
            if (element % 2 == 0) sum += element;
        }
        return sum;
    }

    private static long[] fibonacciNumbersUntil(long limit) {
        List<Long> fibonacciNumbers = new ArrayList<>();
        fibonacciNumbers.add(1L);
        long fibo1 = 1L;
        fibonacciNumbers.add(2L);
        long fibo2 = 2L;
        long temp;

        while (fibo2 < limit) {
            temp = fibo2;
            fibo2 += fibo1;
            fibo1 = temp;
            fibonacciNumbers.add(fibo2);
        }

        return Converter.listToArr(fibonacciNumbers);
    }
}
