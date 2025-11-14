package euler;

import utils.Converter;
import utils.Primes;

import java.util.ArrayList;
import java.util.List;

public class PE_051 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int size = 8;
        int[] first = firstPrimeDigitReplacementFamily(size);
        List<Integer> score = score(first);
        return makeNumber(first, score.getFirst());
    }

    private static int[] firstPrimeDigitReplacementFamily(int familySize) {
        int targetDigitCount = 2;
        int[] firstPrimeDigitReplacementFamily = new int[0];
        int injectionAmountStep = 1;
        if (familySize > 7) injectionAmountStep = 3;

        int smallestPrimeWithProperty = -1;
        while (smallestPrimeWithProperty < 0) {
            for (int amountOfInjections = injectionAmountStep; amountOfInjections < targetDigitCount; amountOfInjections+=injectionAmountStep) {
                int amountOfStartingDigits = targetDigitCount-amountOfInjections;
                int limit = (int) Math.pow(10, amountOfStartingDigits);
                for (int i = limit / 10; i < limit; i++) {
                    int[] digits = Converter.digitArray(i);
                    int[] bestInjection = bestInjectionCombination(digits, amountOfInjections);
                    List<Integer> score = score(bestInjection);
                    if (score.size() >= familySize) {
                        int firstNumber = makeNumber(bestInjection, score.getFirst());
                        if (smallestPrimeWithProperty == -1 || smallestPrimeWithProperty > firstNumber) {
                            smallestPrimeWithProperty = firstNumber;
                            firstPrimeDigitReplacementFamily = bestInjection;
                        }
                    }
                }
            }
            targetDigitCount++;
        }

        return firstPrimeDigitReplacementFamily;
    }

    private static int[] injectedNumber(int[] digits, List<Integer> injections) {
        int[] number = new int[digits.length + injections.size()];
        int injectionIndex = 0;
        for (int i = 0; i < digits.length; i++) {
            while (injectionIndex < injections.size() && injections.get(injectionIndex) == i) {
                number[i + injectionIndex] = -1;
                injectionIndex++;
            }
            number[i + injectionIndex] = digits[i];
        }
        return number;
    }

    private static List<Integer> score(int[] number) {
        List<Integer> score = new ArrayList<>();
        int start = 0;
        if (number[0] == -1) start = 1;
        for (int i = start; i < 10; i++) {
            int numberInt = 0;
            for (int digit : number) {
                if (digit == -1) digit = i;
                numberInt = 10 * numberInt + digit;
            }
            if (Primes.isPrime(numberInt)) score.add(i);
        }
        return score;
    }

    private static int makeNumber(int[] number, int digit) {
        int result = 0;
        for (int i : number) {
            int d;
            if (i == -1) d = digit;
            else d = i;
            result = result * 10 + d;
        }
        return result;
    }

    private static int[] bestInjectionCombination(int[] digits, int n) {
        return bestInjectionCombination(digits, n, new ArrayList<>());
    }

    private static int[] bestInjectionCombination(int[] digits, int n, List<Integer> injections) {
        if (n == 0) {
            return injectedNumber(digits, injections);
        }
        int[] bestNumber = null;
        int bestScore = -1;
        int start = 0;
        if (!injections.isEmpty()) start = injections.getLast();
        for (int i = start; i < digits.length; i++) {
            injections.add(i);
            int[] number = bestInjectionCombination(digits, n-1, injections);
            injections.removeLast();
            List<Integer> score = score(number);
            if (score.size() > bestScore) {
                bestScore = score.size();
                bestNumber = number;
            }
        }
        return bestNumber;
    }
}
