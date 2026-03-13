package euler;

import utils.Diophantine;
import utils.Primes;

import java.util.ArrayList;
import java.util.List;

public class PE_387 {
    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        int powTen = 14;
        long result = sumOfStrongRightTruncatableHarshadPrimes(powTen);
        return String.valueOf(result);
    }

    private static long sumOfStrongRightTruncatableHarshadPrimes(int powTenLimit) {
        long sum = 0;
        List<Long> working1 = List.of(1L, 3L, 5L, 7L, 9L);
        List<Long> working2 = List.of(2L, 4L, 6L, 8L);
        for (int pow = 2; pow < powTenLimit; pow++) {
            List<Long> newWorking1 = new ArrayList<>();
            for (Long l : working1) {
                int digitSum = Diophantine.digitSum(l);
                for (int i = 0; i < 10; i+=2) {
                    long n = 10*l + i;
                    if (n % (digitSum+i) != 0) continue;
                    newWorking1.add(n);
                    if (Primes.isPrime(n / (digitSum+i))) {
                        for (int j = 1; j < 10; j+=2) {
                            long N = 10*n + j;
                            if (Primes.isPrime(N)) {
                                sum += N;
                            }
                        }
                    }
                }
            }
            working1 = newWorking1;
            List<Long> newWorking2 = new ArrayList<>();
            for (Long l : working2) {
                int digitSum = Diophantine.digitSum(l);
                for (int i = 0; i < 10; i++) {
                    long n = 10*l + i;
                    if (n % (digitSum+i) != 0) continue;
                    if (i % 2 == 1) {
                        newWorking1.add(n);
                    } else {
                        newWorking2.add(n);
                    }
                    if (Primes.isPrime(n / (digitSum+i))) {
                        for (int j = 1; j < 10; j+=2) {
                            long N = 10*n + j;
                            if (Primes.isPrime(N)) {
                                sum += N;
                            }
                        }
                    }
                }
            }
            working2 = newWorking2;
        }
        return sum;
    }
}
