package euler;

import utils.Combinations;
import utils.Converter;

public class PE_024 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] permutation = Combinations.nthPermutation(arr, 999_999);
        return Converter.fromDigitArray(permutation);
    }
}
