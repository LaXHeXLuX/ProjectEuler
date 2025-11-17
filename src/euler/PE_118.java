package euler;

import utils.Primes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PE_118 {
    private static final Map<Integer, Integer> keyIndexMap = new HashMap<>();
    private static final List<Integer> primePermKey = new ArrayList<>();
    private static final List<Integer> primePermCount = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        return countOfPandigitalPrimeSets(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    private static int sum(List<Integer> list) {
        int sum = 0;
        for (Integer i : list) sum += i;
        return sum;
    }

    private static long countOfPandigitalPrimeSets(List<Integer> digits) {
        int limitDigitAmount = digits.size();
        int digitSum = sum(digits);
        if (digitSum % 3 == 0) limitDigitAmount--;

        int limit = (int) Math.pow(10, limitDigitAmount-1) * (digits.getLast()+1);
        makePrimePermValueCount(limit, digits);
        return countOfPandigitalPrimeSets(0, 1, digits);
    }

    private static int smallestPermutation(int n) {
        int[] digits = new int[10];
        while (n > 0) {
            digits[n % 10]++;
            n /= 10;
        }

        int perm = 0;
        for (int i = 0; i < digits.length; i++) {
            for (int j = 0; j < digits[i]; j++) {
                perm = perm * 10 + i;
            }
        }

        return perm;
    }

    private static long countOfPandigitalPrimeSets(int lastIndex, int primeCount, List<Integer> digits) {
        if (digits.isEmpty()) {
            return primeCount;
        }

        long sum = 0;
        for (int i = lastIndex+1; i < primePermKey.size(); i++) {
            int prime = primePermKey.get(i);
            List<Integer> newDigits = newDigits(prime, digits);
            if (newDigits != null) sum += countOfPandigitalPrimeSets(i, primeCount * primePermCount.get(i), newDigits);
        }

        return sum;
    }

    private static List<Integer> newDigits(int n, List<Integer> digits) {
        List<Integer> newDigits = new ArrayList<>(digits);

        while (n > 0) {
            Integer last = n % 10;
            if (!newDigits.remove(last)) return null;
            n /= 10;
        }

        return newDigits;
    }

    private static void makePrimePermValueCount(int limit, List<Integer> digits) {
        int[] digitsArr = new int[10];
        for (Integer digit : digits) {
            digitsArr[digit]++;
        }

        makePrimePermValueCount(limit, digitsArr, 0);
    }

    private static void makePrimePermValueCount(int limit, int[] digits, int currentPrime) {
        if (currentPrime > limit) return;

        for (int i = 0; i < 10; i++) {
            if (digits[i] <= 0) continue;
            int newPrime = currentPrime * 10 + i;
            if (Primes.isPrime(newPrime)) {
                int smallestPermutation = smallestPermutation(newPrime);
                if (!keyIndexMap.containsKey(smallestPermutation)) {
                    keyIndexMap.put(smallestPermutation, primePermKey.size());
                    primePermKey.add(smallestPermutation);
                    primePermCount.add(1);
                }
                else {
                    int index = keyIndexMap.get(smallestPermutation);
                    primePermCount.set(index, primePermCount.get(index) + 1);
                }
            }
            digits[i]--;
            makePrimePermValueCount(limit, digits, newPrime);
            digits[i]++;
        }
    }
}
