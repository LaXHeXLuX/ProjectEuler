package UsefulFunctions;

import java.util.ArrayList;
import java.util.List;

public class Divisors {
    public long[] divisors(long n) {
        if (n < 1) return new long[] {};
        if (n == 1) return new long[] {1};
        List<Long> divisors = new ArrayList<>();
        divisors.add(1L);
        List<Long> biggerDivisors = new ArrayList<>();
        for (long i = 2; i <= Math.pow(n, 0.5); i++) {
            if (n % i == 0) {
                divisors.add(i);
                long counterPart = n/i;
                if (counterPart != i) biggerDivisors.add(counterPart);
            }
        }
        for (int i = biggerDivisors.size()-1; i >= 0; i--) {
            divisors.add(biggerDivisors.get(i));
        }
        divisors.add(n);
        Converter c = new Converter();
        return c.listToArrLong(divisors);
    }
    public long sumOfDivisors(long n) {
        long[] divisors = divisors(n);
        long sum = 0;
        for (int i = 0; i < divisors.length-1; i++) {
            sum += divisors[i];
        }
        return sum;
    }
}
