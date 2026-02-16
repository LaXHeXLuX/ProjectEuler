package euler;

import utils.Primes;

import java.util.Arrays;

public class PE_050 {
    private static boolean[] composites;
    private static int[] primes;
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000_000;
        composites = Primes.compositeSieve(limit);
        primes = Primes.primes(composites);

        int[] combination = largestArrayOfConsecutivePrimesSummingToPrime();
        return (sumOfArray(combination));
    }

    private static int sumOfArray(int[] arr) {
        int sum = 0;
        for (int i : arr) sum += i;
        return sum;
    }

    private static int[] largestArrayOfConsecutivePrimesSummingToPrime() {
        int largestStart = 0;
        int largestEnd = 0;
        for (int startIndex = 0; ; startIndex++) {
            if (startIndex + (largestEnd-largestStart) > composites.length) break;
            int sum = 0;
            boolean flag = true;
            int i;
            for (i = 0; i < largestEnd-largestStart; i++) {
                if (startIndex+i >= primes.length) {
                    flag = false;
                    break;
                }
                sum += primes[startIndex + i];
                if (sum >= primes[primes.length-1]) {
                    flag = false;
                    break;
                }
            }
            if (!flag) continue;
            sum += primes[startIndex + i];
            while (sum <= primes[primes.length-1]) {
                if (!composites[sum >> 1]) {
                    largestStart = startIndex;
                    largestEnd = startIndex + i;
                }
                i++;
                sum += primes[startIndex + i];
            }
            if (i < largestEnd-largestStart) break;
        }
        return Arrays.copyOfRange(primes, largestStart, largestEnd+1);
    }
}
