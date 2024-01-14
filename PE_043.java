import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_043 {
    public static void main(String[] args) {
        long[] numbers = findNumbersWithSubStringDivisibility();
        System.out.println(Arrays.toString(numbers));

        long sum = 0;
        for(long number : numbers) sum += number;
        System.out.println(sum);
    }

    private static long[] findNumbersWithSubStringDivisibility() {
        List<Long> numbers = new ArrayList<>();
        int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[][] permutations = Combinations.findPermutations(digits);

        for (int[] permutation : permutations) {
            permutation = Converter.digitArray(Converter.digitFromArrayLong(permutation));
            if (!Pandigital.isPandigital(permutation, 0)) continue;
            long number = Converter.digitFromArrayLong(permutation);
            if (!hasSubStringDivisibility(number)) continue;
            numbers.add(number);
        }

        return Converter.listToArr(numbers);
    }

    private static boolean hasSubStringDivisibility(long n) {
        int[] dividers = {1, 2, 3, 5, 7, 11, 13, 17, 23, 29, 31, 37, 41, 43, 47, 53};
        int lengthOfOne = 3;
        long[] subStrings = divideIntoSubStrings(n, lengthOfOne);

        for (int i = 1; i < subStrings.length; i++) {
            if (subStrings[i] % dividers[i] != 0) return false;
        }

        return true;
    }

    private static long[] divideIntoSubStrings(long n, int lengthOfOneSubString) {
        int[] digitArray = Converter.digitArray(n);

        int[][] subStringDigitArrays = new int[digitArray.length+1-lengthOfOneSubString][];
        for (int i = 0; i < subStringDigitArrays.length; i++) {
            subStringDigitArrays[i] = ArrayFunctions.subArray(digitArray, i, i+lengthOfOneSubString-1);
        }

        long[] subStrings = new long[subStringDigitArrays.length];
        for (int i = 0; i < subStrings.length; i++) {
            subStrings[i] = Converter.digitFromArrayLong(subStringDigitArrays[i]);
        }

        return subStrings;
    }
}
