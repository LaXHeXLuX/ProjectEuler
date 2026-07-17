package euler;

import utils.Primes;

public class PE_0387 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int powTen = 14;
        return String.valueOf(sumOfHarshadPrimes(powTen));
    }

    private static long sumOfHarshadPrimes(int digits) {
        long sum = 0;
        for (int i = 1; i < 10; i++) {
            sum += sumOfHarshadPrimes(i, i, digits-1);
        }
        return sum;
    }

    private static long sumOfHarshadPrimes(long n, int sum, int digits) {
        if (n % sum != 0) return 0;

        long result = 0;
        if (Primes.isPrime(n / sum)) {
            for (int i = 1; i < 10; i += 2) {
                long p = 10*n + i;
                if (Primes.isPrime(p)) result += p;
            }
        }
        if (digits == 1) return result;

        for (int i = 0; i < 10; i++) {
            result += sumOfHarshadPrimes(10*n + i, sum + i, digits-1);
        }

        return result;
    }
}
