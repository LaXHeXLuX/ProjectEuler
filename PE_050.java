import UsefulFunctions.Converter;
import UsefulFunctions.Primes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_050 {
    public static void main(String[] args) {
        int limit = 1_000_000;

        int[] combination = largestArrayOfConsecutivePrimes(limit);
        System.out.println();
        System.out.println(Arrays.toString(combination));
        System.out.println(combination.length);
        System.out.println(sumOfArray(combination));
    }

    private static int sumOfArray(int[] arr) {
        int sum = 0;
        for (int a : arr) sum += a;
        return sum;
    }

    private static int[] largestArrayOfConsecutivePrimes(int limit) {
        boolean[] primesBool = Primes.sieveOfPrimes(limit);
        int[] primes = Converter.booleanArrToIntArr(primesBool);
        int[] sums = generateSums(primes);

        int[] largest = {};
        int minPrime = 2;
        for (int i = primes.length-1; primes[i] >= minPrime; i--) {
            int[] combination = findConsecutivePrimesThatSumToN(primes[i], primes);
            if (combination.length > largest.length) {
                largest = combination;
                minPrime = sums[largest.length];
            }
        }

        return largest;
    }

    private static int[] generateSums(int[] arr) {
        int[] sums = new int[arr.length];
        sums[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            sums[i] = sums[i-1] + arr[i];
        }

        return sums;
    }

    private static int[] findConsecutivePrimesThatSumToN(int n , int[] primes) {

        for (int startingPrime = 0; startingPrime < primes.length; startingPrime++) {
            int sum = primes[startingPrime];
            if (sum == n) return new int[] {};
            List<Integer> currentPrimes = new ArrayList<>();
            currentPrimes.add(sum);

            for (int i = startingPrime+1; i < primes.length; i++) {
                sum += primes[i];
                currentPrimes.add(primes[i]);
                if (sum >= n) break;
            }

            if (sum == n) return Converter.listToArrInt(currentPrimes);
        }

        return new int[] {};
    }
}
