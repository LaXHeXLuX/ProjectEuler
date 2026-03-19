package euler;

import utils.Combinations;
import utils.Converter;

public class PE_024 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int n = 1_000_000;
        int[] permutation = Combinations.nthPermutation(arr, n-1);
        return String.valueOf(Converter.fromDigitArray(permutation));
    }
}
