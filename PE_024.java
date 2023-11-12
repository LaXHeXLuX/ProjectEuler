import UsefulFunctions.ArrayFunctions;
import UsefulFunctions.Combinations;

import java.util.Arrays;

public class PE_024 {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[][] permutations = findPermutations(arr);
        System.out.println(Arrays.toString(permutations[1_000_000-1]));
    }
    private static int[][] findPermutations(int[] arr) {
        if (arr.length == 0) return new int[][] {};
        if (arr.length == 1) return new int[][] {arr};
        int[][] permutations = new int[(int) Combinations.factorialLong(arr.length)][];
        arr = ArrayFunctions.mergeSort(arr);
        int[] newArr0 = new int[arr.length];
        System.arraycopy(arr, 0, newArr0, 0, newArr0.length);
        permutations[0] = newArr0;
        for (int i = 1; i < permutations.length; i++) {
            arr = swapAndSort(arr);
            int[] newArr = new int[arr.length];
            System.arraycopy(arr, 0, newArr, 0, newArr.length);
            permutations[i] = newArr;
        }
        return permutations;
    }
    private static int[] swapAndSort(int[] arr) {
        int i;
        for (i = arr.length - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                break;
            }
        }
        int ceilingIndex = i + 1;
        for (int j = i+1; j < arr.length; j++) {
            if (arr[j] <= arr[i]) continue;
            if (arr[j] < arr[ceilingIndex]) ceilingIndex = j;
        }
        int temp = arr[i];
        arr[i] = arr[ceilingIndex];
        arr[ceilingIndex] = temp;

        int[] arrToBeSorted = new int[arr.length-i-1];
        System.arraycopy(arr, i+1, arrToBeSorted, 0, arrToBeSorted.length);
        arrToBeSorted = ArrayFunctions.mergeSort(arrToBeSorted);
        int[] restOfArr = new int[i+1];
        System.arraycopy(arr, 0, restOfArr, 0, restOfArr.length);
        return concatenate(restOfArr, arrToBeSorted);
    }
    private static int[] concatenate(int[] arr1, int[] arr2) {
        int[] newArr = new int[arr1.length+ arr2.length];
        System.arraycopy(arr1, 0, newArr, 0, arr1.length);
        System.arraycopy(arr2, 0, newArr, arr1.length, arr2.length);
        return newArr;
    }
}
