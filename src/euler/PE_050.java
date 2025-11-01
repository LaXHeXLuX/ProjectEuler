package euler;

import utils.Converter;
import utils.Primes;

import java.util.Arrays;

public class PE_050 {
    private static boolean[] primesBool;
    private static int[] primes;
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000_000;
        primesBool = Primes.sieveOfPrimes(limit);
        primes = Converter.booleanArrToIntArr(primesBool);

        int[] combination = largestArrayOfConsecutivePrimesSummingToPrime();
        return (sumOfArray(combination));
    }

    private static int sumOfArray(int[] arr) {
        int sum = 0;
        for (int a : arr) sum += a;
        return sum;
    }

    private static int[] largestArrayOfConsecutivePrimesSummingToPrime() {
        int largestStart = 0;
        int largestEnd = 0;
        for (int startIndex = 0; ; startIndex++) {
            if (startIndex + (largestEnd-largestStart) > primesBool.length) break;
            int sum = 0;
            boolean flag = true;
            int i;
            for (i = 0; i < largestEnd-largestStart; i++) {
                if (startIndex+i >= primes.length) {
                    flag = false;
                    break;
                }
                sum += primes[startIndex + i];
                if (sum >= primesBool.length) {
                    flag = false;
                    break;
                }
            }
            if (!flag) continue;
            sum += primes[startIndex + i];
            while (sum < primesBool.length) {
                if (primesBool[sum]) {
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
