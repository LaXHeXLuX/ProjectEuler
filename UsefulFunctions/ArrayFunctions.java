package UsefulFunctions;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ArrayFunctions {
    public static boolean contains(String el, String[] arr) {
        for (String element : arr) {
            if (Objects.equals(el, element)) return true;
        }
        return false;
    }
    public static boolean contains(int el, int[] arr) {
        for (int element : arr) {
            if (el == element) return true;
        }
        return false;
    }
    public static boolean contains(long el, long[] arr) {
        for (long element : arr) {
            if (el == element) return true;
        }
        return false;
    }
    public static boolean contains(BigInteger el, BigInteger[] arr) {
        for (BigInteger element : arr) {
            if (Objects.equals(el, element)) return true;
        }
        return false;
    }
    public static boolean contains(String el, List<String> arr) {
        for (String element : arr) {
            if (Objects.equals(el, element)) return true;
        }
        return false;
    }
    public static boolean contains(int el, List<Integer> arr) {
        for (int element : arr) {
            if (el == element) return true;
        }
        return false;
    }
    public static boolean contains(long el, List<Long> arr) {
        for (long element : arr) {
            if (el == element) return true;
        }
        return false;
    }
    public static boolean contains(BigInteger el, List<BigInteger> arr) {
        for (BigInteger element : arr) {
            if (Objects.equals(el, element)) return true;
        }
        return false;
    }
    public static boolean sortedContains(int el, int[] arr) {
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
    public static boolean sortedContains(BigInteger el, BigInteger[] arr) {
        if (arr.length == 0) return false;
        if (arr.length == 1) return Objects.equals(arr[0], el);
        int lowIndex = 0;
        int highIndex = arr.length-1;
        while (lowIndex != highIndex) {
            int middleIndex = (lowIndex + highIndex)/2;
            if (arr[middleIndex].compareTo(el) < 0) {
                lowIndex = middleIndex+1;
            }
            else if (arr[middleIndex].compareTo(el) > 0) {
                highIndex = middleIndex;
            }
            else return true;
        }
        return Objects.equals(arr[lowIndex], el);
    }
    public static String[] mergeSort(String[] arr) {
        if (arr.length == 1) return arr;
        String[] arr1 = new String[arr.length/2];
        String[] arr2 = new String[arr.length - arr.length/2];
        System.arraycopy(arr, 0, arr1, 0, arr1.length);
        System.arraycopy(arr, arr1.length, arr2, 0, arr2.length);
        arr1 = mergeSort(arr1);
        arr2 = mergeSort(arr2);
        String[] sorted = new String[arr.length];
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < sorted.length; i++) {
            if (index2 == arr2.length || index1 < arr1.length && arr1[index1].compareTo(arr2[index2]) <= 0) {
                sorted[i] = arr1[index1];
                index1++;
            } else {
                sorted[i] = arr2[index2];
                index2++;
            }
        }
        return sorted;
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
    public static long[] removeIndex(long[] arr, int index) {
        long[] newArr = new long[arr.length-1];
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
    public static long[] subArray(long[] arr, int startIndex, int endIndex) {
        long[] newArr = new long[endIndex-startIndex+1];
        System.arraycopy(arr, startIndex, newArr, 0, newArr.length);
        return newArr;
    }
    public static int[] concatenate(int[] arr1, int[] arr2) {
        int[] newArr = new int[arr1.length+ arr2.length];
        System.arraycopy(arr1, 0, newArr, 0, arr1.length);
        System.arraycopy(arr2, 0, newArr, arr1.length, arr2.length);
        return newArr;
    }
}
