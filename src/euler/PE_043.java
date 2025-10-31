package src.euler;

import java.util.HashSet;
import java.util.Set;

public class PE_043 {
    private static final int[] primes = {2, 3, 5, 7, 11, 13, 17};

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        Set<String> numbers = pandigitalNumbersWithSubstringDivisibility();
        return sum(numbers);
    }
    
    private static long sum(Set<String> set) {
        long sum = 0;
        for (String s : set) {
            sum += Long.parseLong(s);
        }
        return sum;
    }

    private static Set<String> pandigitalNumbersWithSubstringDivisibility() {
        return pandigitalNumbersWithSubstringDivisibilityOfSize(7, "");
    }
    
    private static Set<String> pandigitalNumbersWithSubstringDivisibilityOfSize(int size, String currentNumber) {
        Set<String> result = new HashSet<>();
        if (size == 0) {
            Set<Character> remaining = extraDigits(currentNumber);
            for (Character c : remaining) {
                result.add(c + currentNumber);
            }
            return result;
        }
        if (size == 7) {
            for (int i = 0; i < 1000; i+=17) {
                String number = String.format("%03d", i);
                if (i % 17 != 0) continue;
                if (hasDuplicates(number)) continue;
                result.addAll(pandigitalNumbersWithSubstringDivisibilityOfSize(size-1, number));
            }
            return result;
        }
        for (int i = 0; i < 10; i++) {
            String number = i + currentNumber;
            long n = Long.parseLong(number);
            for (int j = 0; j < 7-size; j++) {
                n /= 10;
            }
            if (n % primes[size-1] != 0) continue;
            if (hasDuplicates(number)) continue;
            result.addAll(pandigitalNumbersWithSubstringDivisibilityOfSize(size-1, number));
        }
        return result;
    }

    private static boolean hasDuplicates(String n) {
        Set<Character> elements = new HashSet<>();
        for (int i = 0; i < n.length(); i++) {
            char c = n.charAt(i);
            if (elements.contains(c)) return true;
            elements.add(c);
        }

        return false;
    }
    
    private static Set<Character> extraDigits(String n) {
        Set<Character> digits = new HashSet<>(Set.of('1', '2', '3', '4', '5', '6' ,'7', '8', '9', '0'));
        for (int i = 0; i < n.length(); i++) {
            char c = n.charAt(i);
            digits.remove(c);
        }
        return digits;
    }
}
