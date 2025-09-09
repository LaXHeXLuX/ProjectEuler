import util.Converter;
import util.Primes;

import java.util.ArrayList;
import java.util.List;

public class PE_037 {
    private static boolean[] primes;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int[] all = findAllTruncatablePrimes();

        long sum = 0;
        for (long a : all) sum += a;
        return sum;
    }

    private static boolean isTruncatablePrime(int n) {
        int x = n;
        while (x > 0) {
            if (!primes[x]) return false;
            x /= 10;
        }
        x = 10;
        while (x < n) {
            int truncation = n % x;
            if (!primes[truncation]) return false;
            x *= 10;
        }
        return true;
    }

    private static List<Integer> findAllTruncatablePrimesHelper(int digitAmount, int lastDigit) {
        if (digitAmount == 0) return new ArrayList<>();
        List<Integer> truncatablePrimes = new ArrayList<>();
        if (digitAmount == 1) {
            truncatablePrimes.add(2);
            truncatablePrimes.add(3);
            truncatablePrimes.add(5);
            truncatablePrimes.add(7);
            return truncatablePrimes;
        }

        int[] options = new int[] {1, 3, 7, 9};
        for (int option : options) {
            List<Integer> nextTruncatablePrimes = findAllTruncatablePrimesHelper(digitAmount-1, option);
            for (Integer ntp : nextTruncatablePrimes) {
                int truncatablePrimeContender = ntp * 10 + option;
                if (primes[truncatablePrimeContender]) {
                    truncatablePrimes.add(truncatablePrimeContender);
                }
            }
        }

        return truncatablePrimes;
    }

    private static int[] findAllTruncatablePrimes() {
        primes = Primes.sieveOfPrimes(1_000_000);

        List<Integer> truncatables = new ArrayList<>();

        int digitAmount = 2;
        while (truncatables.size() < 11) {
            List<Integer> truncatablePrimeContenders = findAllTruncatablePrimesHelper(digitAmount, -1);
            for (Integer tpc : truncatablePrimeContenders) {
                if (isTruncatablePrime(tpc)) truncatables.add(tpc);
            }
            digitAmount++;
        }

        return Converter.listToArr(truncatables);
    }
}
