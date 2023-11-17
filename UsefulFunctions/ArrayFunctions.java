package UsefulFunctions;

import java.util.Arrays;
import java.util.List;

public class ArrayFunctions {
    public static boolean contains(int el, int[] arr) {
        for (int element : arr) {
            if (el == element) return true;
        }
        return false;
    }
    public static boolean contains(int el, List<Integer> arr) {
        for (int element : arr) {
            if (el == element) return true;
        }
        return false;
    }
    public static boolean sortedContains(long el, long[] arr) {
        if (arr.length == 0) return false;
        if (arr.length == 1) return arr[0] == el;
        int lowIndex = 0;
        int highIndex = arr.length-1;
        while (lowIndex != highIndex) {
            int middleIndex = (lowIndex + highIndex)/2;
            if (arr[middleIndex] < el) {
                lowIndex = middleIndex+1;
            }
            else if (arr[middleIndex] > el) {
                highIndex = middleIndex;
            }
            else return true;
        }
        return arr[lowIndex] == el;
    }
    public static int[] switchElement( int[] arr, int oldElement, int newElement) {
        int[] newArr = new int[arr.length];
        System.arraycopy(arr, 0, newArr, 0, newArr.length);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == oldElement) {
                newArr[i] = newElement;
                return newArr;
            }
        }

        return newArr;
    }
    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) return arr;
        int[] arr1 = new int[arr.length/2];
        int[] arr2 = new int[arr.length - arr.length/2];
        System.arraycopy(arr, 0, arr1, 0, arr1.length);
        System.arraycopy(arr, arr1.length, arr2, 0, arr2.length);
        arr1 = mergeSort(arr1);
        arr2 = mergeSort(arr2);
        int[] sorted = new int[arr.length];
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < sorted.length; i++) {
            if (index2 == arr2.length || index1 < arr1.length && arr1[index1] <= arr2[index2]) {
                sorted[i] = arr1[index1];
                index1++;
            } else {
                sorted[i] = arr2[index2];
                index2++;
            }
        }
        return sorted;
    }
    public static long[] mergeSort(long[] arr)  {
        if (arr.length == 1) return arr;
        long[] arr1 = new long[arr.length/2];
        long[] arr2 = new long[arr.length - arr.length/2];
        System.arraycopy(arr, 0, arr1, 0, arr1.length);
        System.arraycopy(arr, arr1.length, arr2, 0, arr2.length);
        arr1 = mergeSort(arr1);
        arr2 = mergeSort(arr2);
        long[] sorted = new long[arr.length];
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < sorted.length; i++) {
            if (index2 == arr2.length || index1 < arr1.length && arr1[index1] <= arr2[index2]) {
                sorted[i] = arr1[index1];
                index1++;
            } else {
                sorted[i] = arr2[index2];
                index2++;
            }
        }
        return sorted;
    }
    public static int[] removeIndex(int[] arr, int index) {
        int[] newArr = new int[arr.length-1];
        System.arraycopy(arr, 0, newArr, 0, index);
        System.arraycopy(arr, index+1, newArr, index, newArr.length-index);
        return newArr;
    }
    public static int[] subArray(int[] arr, int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex >= arr.length) throw new RuntimeException("invalid parameters: " + Arrays.toString(arr) + ", " + startIndex + ", " + endIndex);
        int[] newArr = new int[endIndex-startIndex+1];
        System.arraycopy(arr, startIndex, newArr, 0, newArr.length);
        return newArr;
    }
    public static int[] concatenate(int[] arr1, int[] arr2) {
        int[] newArr = new int[arr1.length+ arr2.length];
        System.arraycopy(arr1, 0, newArr, 0, arr1.length);
        System.arraycopy(arr2, 0, newArr, arr1.length, arr2.length);
        return newArr;
    }
    public static int[] add(int[] arr, int el) {
        int[] newArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i] + el;
        }

        return newArr;
    }
    public static long[] add(long[] arr, long el) {
        long[] newArr = new long[arr.length];

        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i] + el;
        }

        return newArr;
    }
    public static int[] multiply(int[] arr, int el) {
        int[] newArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i]*el;
        }

        return newArr;
    }
    public static long[] multiply(long[] arr, long el) {
        long[] newArr = new long[arr.length];

        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i]*el;
        }

        return newArr;
    }
}
