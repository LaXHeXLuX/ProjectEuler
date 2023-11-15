import UsefulFunctions.Combinations;
import UsefulFunctions.Converter;
import UsefulFunctions.SieveOfPrimes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_051 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int size = 9;
        int[][] first = firstPrimeDigitReplacementFamily(size);
        for (int[] f : first) System.out.println(Arrays.toString(f));

        long end = System.currentTimeMillis();
        System.out.println("TIME: " + (end-start));

    }

    private static int[][] firstPrimeDigitReplacementFamily(int familySize) {
        long start = System.currentTimeMillis();
        boolean[] primes = SieveOfPrimes.sieveOfPrimes((int) Math.pow(10, familySize-1));
        long end = System.currentTimeMillis();
        System.out.println("Primes found time: " + (end-start));
        int targetDigitCount = 2;
        int[][] firstPrimeDigitReplacementFamily = new int[][] {};

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
                        int firstNumber = (int) Converter.digitFromArrayLong(bestInjectionFamily[0]);
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
        int[][] bestInjectionCombinationFamily = new int[][] {};

        for(int[] injectionCombination : injectionCombinations) {
            int[][] goodInjections = injectionsThatArePrime(digits, injectionCombination, primes);
            if (goodInjections.length == 0) continue;
            int firstNumber = (int) Converter.digitFromArrayLong(goodInjections[0]);
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
        //System.out.println("injectionsThatArePrime. d: " + Arrays.toString(digits) + ", places: " + Arrays.toString(injectionPlaces));
        List<int[]> injectedNumbersThatArePrime = new ArrayList<>();
        int start = 0;
        if (injectionPlaces[0] == 0) start = 1;

        for (int i = start; i < 10; i++) {
            int[] injectionPlacesCopy = new int[injectionPlaces.length];
            System.arraycopy(injectionPlaces, 0, injectionPlacesCopy, 0, injectionPlacesCopy.length);
            int[] injectedNumber = injectDigit(digits, injectionPlacesCopy, i);
            if (primes[(int) Converter.digitFromArrayLong(injectedNumber)]) injectedNumbersThatArePrime.add(injectedNumber);
        }
        //System.out.println("returning: " + Arrays.deepToString(Converter.arrListToArrInt(injectedNumbersThatArePrime)));
        return Converter.arrListToArrInt(injectedNumbersThatArePrime);
    }

    private static int[] injectDigit(int[] digits, int[] injectionPlaces, int injectableDigit) {
        //System.out.println("injectDigit. d: " + Arrays.toString(digits) + ", places: " + Arrays.toString(injectionPlaces) + ", adding: " + injectableDigit);
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
