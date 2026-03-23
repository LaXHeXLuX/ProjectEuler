package euler;

import utils.Primes;

public class PE_050 {
    private static boolean[] composites;
    private static int[] primes;
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 1_000_000;
        composites = Primes.compositeSieve(limit);
        primes = Primes.primes(composites);

        int result = sumOfConsecutivePrimesSummingToPrime();
        return String.valueOf(result);
    }

    private static int sumOfConsecutivePrimesSummingToPrime() {
        int largestLength = 0;
        int largestSum = 0;
        for (int startIndex = 0; ; startIndex++) {
            if (startIndex + largestLength > composites.length) break;
            int sum = 0;
            boolean flag = true;
            int i;
            for (i = 0; i < largestLength; i++) {
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
                    largestLength = i;
                    largestSum = sum;
                }
                i+=2;
                sum += primes[startIndex + i - 1] + primes[startIndex + i];
            }
            if (i < largestLength) break;
        }
        return largestSum;
    }
}
