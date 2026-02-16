package euler;

import utils.Primes;

import java.util.ArrayList;
import java.util.List;

public class PE_037 {
    private static boolean[] composites;

    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        List<Integer> all = allTruncatablePrimes();

        long sum = 0;
        for (long a : all) sum += a;
        return sum;
    }

    private static boolean isTruncatablePrime(int n) {
        int x = n;
        while (x > 0) {
            if (composites[x >> 1]) return false;
            x /= 10;
        }
        x = 10;
        while (x < n) {
            int truncation = n % x;
            if (composites[truncation >> 1]) return false;
            x *= 10;
        }
        return true;
    }

    private static List<Integer> allTruncatablePrimesHelper(int digitAmount) {
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
            List<Integer> nextTruncatablePrimes = allTruncatablePrimesHelper(digitAmount-1);
            for (Integer ntp : nextTruncatablePrimes) {
                int truncatablePrimeContender = ntp * 10 + option;
                if (!composites[truncatablePrimeContender >> 1]) {
                    truncatablePrimes.add(truncatablePrimeContender);
                }
            }
        }

        return truncatablePrimes;
    }

    private static List<Integer> allTruncatablePrimes() {
        composites = Primes.compositeSieve(1_000_000);

        List<Integer> truncatables = new ArrayList<>();

        int digitAmount = 2;
        while (truncatables.size() < 11) {
            List<Integer> truncatablePrimeContenders = allTruncatablePrimesHelper(digitAmount);
            for (Integer tpc : truncatablePrimeContenders) {
                if (isTruncatablePrime(tpc)) truncatables.add(tpc);
            }
            digitAmount++;
        }

        return truncatables;
    }
}
