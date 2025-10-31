package src.euler;

import util.Combinations;
import util.Converter;

public class PE_024 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] permutation = Combinations.nthPermutation(arr, 999_999);
        return Converter.fromDigitArray(permutation);
    }
}
