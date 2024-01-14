import java.util.Arrays;

public class PE_024 {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[][] permutations = Combinations.findPermutations(arr);
        System.out.println(Arrays.toString(permutations[1_000_000-1]));
    }
}
