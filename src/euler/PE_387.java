package euler;

import utils.Diophantine;
import utils.Primes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_387 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int powTen = 14;
        long result = sumOfStrongRightTruncatableHarshadPrimes(powTen);
        return String.valueOf(result);
    }

    private static long sumOfStrongRightTruncatableHarshadPrimes(int powTenLimit) {
        long sum = 0;

        List<Long> working1 = Arrays.asList(1L, 3L, 5L, 7L, 9L);
        List<Long> working2 = Arrays.asList(2L, 4L, 6L, 8L);
        for (int pow = 2; pow < powTenLimit; pow++) {
            working1 = newWorking(working1, null);
            sum += working1.removeLast();
            working2 = newWorking(working2, working1);
            sum += working2.removeLast();
        }

        return sum;
    }

    private static List<Long> newWorking(List<Long> working, List<Long> workingOdd) {
        List<Long> newWorking = new ArrayList<>();
        long sum = 0;
        for (Long l : working) {
            int digitSum = Diophantine.digitSum(l);
            newWorking.add(10*l);
            int step = 1;
            if (workingOdd == null) step = 2;
            for (int i = step; i < 10; i+=step) {
                long n = 10*l + i;
                if (n % (digitSum+i) != 0) continue;
                if (workingOdd == null || i % 2 == 0) newWorking.add(n);
                else workingOdd.add(n);
                if (Primes.isPrime(n / (digitSum+i))) {
                    sum += sumFor(n);
                }
            }
        }
        newWorking.add(sum);
        return newWorking;
    }

    private static long sumFor(long n) {
        long sum = 0;
        for (int i = 1; i < 10; i+=2) {
            long N = 10*n + i;
            if (Primes.isPrime(N)) sum += N;
        }
        return sum;
    }
}
