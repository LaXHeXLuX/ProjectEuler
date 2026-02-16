package euler;

import utils.Primes;

import java.util.ArrayList;
import java.util.List;

public class PE_111 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int digitAmount = 10;
        return primeRunSum(digitAmount);
    }

    private static long primeRunSum(int digitAmount) {
        long primeRunSum = 0;
        for (int runDigit = 0; runDigit < 10; runDigit++) {
            List<Long> primeRuns = primesWithMaxRuns(digitAmount, runDigit);
            long sum = sum(primeRuns);
            primeRunSum += sum;
        }
        return primeRunSum;
    }

    private static long sum(List<Long> list) {
        long sum = 0;
        for (Long i : list) sum += i;
        return sum;
    }

    private static List<Long> primesWithMaxRuns(int digitAmount, int runDigit) {
        if (runDigit == 1 && digitAmount % 3 != 0) {
            long runOfOnes = 0;
            for (int i = 0; i < digitAmount; i++) {
                runOfOnes = runOfOnes * 10 + 1;
            }
            if (Primes.isPrime(runOfOnes)) return List.of(runOfOnes);
        }
        for (int runDigitAmount = digitAmount-1; runDigitAmount > 0; runDigitAmount--) {
            List<Long> allPrimeRuns = primesWithMaxRuns(digitAmount - runDigitAmount, runDigitAmount, runDigit, new ArrayList<>());
            if (!allPrimeRuns.isEmpty()) return allPrimeRuns;
        }
        return List.of();
    }

    private static List<Long> primesWithMaxRuns(int digitAmount, int runDigitAmount, int runDigit, List<Integer> digits) {
        if (digitAmount == 0) {
            return primesWithRun(digits, runDigitAmount, runDigit);
        }
        List<Long> primesWithMaxRuns = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i == runDigit) continue;
            digits.add(i);
            List<Long> currentPrimeRuns = primesWithMaxRuns(digitAmount-1, runDigitAmount, runDigit, digits);
            digits.removeLast();
            primesWithMaxRuns.addAll(currentPrimeRuns);
        }
        return primesWithMaxRuns;
    }

    private static List<Long> primesWithRun(List<Integer> digits, int n, int runDigit) {
        if (digits.contains(runDigit)) return java.util.List.of();
        digits = digits.reversed();
        return primesWithRun(digits, n, runDigit, 0);
    }

    private static List<Long> primesWithRun(List<Integer> digits, int n, int runDigit, long number) {
        if (n == 0) {
            for (int i = digits.size()-1; i >= 0; i--) {
                number = 10*number + digits.get(i);
            }
            if (Primes.isPrime(number)) return List.of(number);
            else return List.of();
        }
        if (digits.isEmpty()) {
            for (int i = 0; i < n; i++) {
                number = 10*number + runDigit;
            }
            if (Primes.isPrime(number)) return List.of(number);
            else return List.of();
        }
        List<Long> primesWithRun = new ArrayList<>();
        if (!(runDigit == 0 && number == 0)) {
            List<Long> result1 = primesWithRun(digits, n-1, runDigit, number * 10 + runDigit);
            primesWithRun.addAll(result1);
        }
        if (!(digits.getLast() == 0 && number == 0)) {
            int lastDigit = digits.removeLast();
            List<Long> result2 = primesWithRun(digits, n, runDigit, number * 10 + lastDigit);
            primesWithRun.addAll(result2);
            digits.add(lastDigit);
        }
        return primesWithRun;
    }
}
