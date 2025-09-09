import util.Combinations;
import util.Converter;
import util.Primes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_051 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int size = 8;
        int[][] first = firstPrimeDigitReplacementFamily(size);
        return Converter.fromDigitArray(first[0]);
    }

    private static int[][] firstPrimeDigitReplacementFamily(int familySize) {
        boolean[] primes = Primes.sieveOfPrimes((int) Math.pow(10, familySize-1));
        int targetDigitCount = 2;
        int[][] firstPrimeDigitReplacementFamily = new int[0][];

        boolean found = false;
        while (!found) {
            int smallestPrimeWithProperty = -1;

            for (int amountOfInjections = 1; amountOfInjections < targetDigitCount; amountOfInjections++) {
                if (amountOfInjections % 3 != 0) continue;
                int amountOfStartingDigits = targetDigitCount-amountOfInjections;

                for (int i = (int) Math.pow(10, amountOfStartingDigits-1); i < Math.pow(10, amountOfStartingDigits); i++) {
                    int[] digits = Converter.digitArray(i);
                    int[][] injectionCombinations = Combinations.combinationsOfGrowingNumbers(0, amountOfStartingDigits, amountOfInjections);
                    int[][] bestInjectionFamily = bestInjectionCombination(injectionCombinations, digits, primes);
                    if (bestInjectionFamily.length >= familySize) {
                        int firstNumber = (int) Converter.fromDigitArray(bestInjectionFamily[0]);
                        found = true;
                        if (smallestPrimeWithProperty == -1 || smallestPrimeWithProperty > firstNumber) {
                            smallestPrimeWithProperty = firstNumber;
                            firstPrimeDigitReplacementFamily = bestInjectionFamily;
                        }
                    }
                }
            }
            
            targetDigitCount++;
        }

        return firstPrimeDigitReplacementFamily;
    }

    private static int[][] bestInjectionCombination(int[][] injectionCombinations, int[] digits, boolean[] primes) {
        int smallestPrimeWithProperty = -1;
        int largestFamilySize = -1;
        int[][] bestInjectionCombinationFamily = new int[0][];

        for(int[] injectionCombination : injectionCombinations) {
            int[][] goodInjections = injectionsThatArePrime(digits, injectionCombination, primes);
            if (goodInjections.length == 0) continue;
            int firstNumber = (int) Converter.fromDigitArray(goodInjections[0]);
            int familySize = goodInjections.length;
            if (familySize > largestFamilySize) {
                largestFamilySize = familySize;
                smallestPrimeWithProperty = firstNumber;
                bestInjectionCombinationFamily = goodInjections;
            }
            else if (smallestPrimeWithProperty == -1 || smallestPrimeWithProperty > firstNumber) {
                smallestPrimeWithProperty = firstNumber;
                bestInjectionCombinationFamily = goodInjections;
            }
        }

        return bestInjectionCombinationFamily;
    }

    private static int[][] injectionsThatArePrime(int[] digits, int[] injectionPlaces, boolean[] primes) {
        List<int[]> injectedNumbersThatArePrime = new ArrayList<>();
        int start = 0;
        if (injectionPlaces[0] == 0) start = 1;

        for (int i = start; i < 10; i++) {
            int[] injectionPlacesCopy = new int[injectionPlaces.length];
            System.arraycopy(injectionPlaces, 0, injectionPlacesCopy, 0, injectionPlacesCopy.length);
            int[] injectedNumber = injectDigit(digits, injectionPlacesCopy, i);
            if (primes[(int) Converter.fromDigitArray(injectedNumber)]) injectedNumbersThatArePrime.add(injectedNumber);
        }

        return Converter.listToArr(injectedNumbersThatArePrime, int[].class);
    }

    private static int[] injectDigit(int[] digits, int[] injectionPlaces, int injectableDigit) {
        int[] newDigits = new int[digits.length + injectionPlaces.length];
        Arrays.fill(newDigits, -1);

        int newDigitIndex;
        for (int i = 0; i < injectionPlaces.length; i++) {
            newDigitIndex = injectionPlaces[i];
            newDigits[newDigitIndex] = injectableDigit;
            for (int j = i+1; j < injectionPlaces.length; j++) {
                injectionPlaces[j]++;
            }
        }

        int digitIndex = 0;
        for (int i = 0; i < newDigits.length; i++) {
            if (newDigits[i] != -1) continue;
            newDigits[i] = digits[digitIndex];
            digitIndex++;
        }

        return newDigits;
    }
}
