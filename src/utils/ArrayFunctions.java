package utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public class ArrayFunctions {

    public static <T> boolean contains(T el, T[] arr) {
        for (T element : arr) {
            if (el.equals(element)) {
                return true;
            }
        }
        return false;
    }
    public static <T> boolean contains(T el, T arr) {
        return contains((Object) el, Converter.toWrapperArray(arr));
    }
    public static int[] reverseArray(int[] arr) {
        int iLimit = arr.length/2;
        for (int i = 0; i < iLimit; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = temp;
        }
        return arr;
    }
    public static <T> T[] reverseArray(T[] arr) {
        T[] reversed = (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length);
        for (int i = 0; i < arr.length; i++) {
            reversed[arr.length - i - 1] = arr[i];
        }
        return reversed;
    }
    public static <T> T removeIndex(T arr, int index) {
        return Converter.toPrimitiveArray(removeIndex(Converter.toWrapperArray(arr), index));
    }
    public static <T> T[] removeIndex(T[] arr, int index) {
        T[] newArr = (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length-1);
        System.arraycopy(arr, 0, newArr, 0, index);
        System.arraycopy(arr, index+1, newArr, index, newArr.length-index);
        return newArr;
    }
    public static <T> T concatenate(T array1, T array2) {
        return Converter.toPrimitiveArray(concatenate(Converter.toWrapperArray(array1), Converter.toWrapperArray(array2)));
    }
    public static <T> T[] concatenate(T[] array1, T[] array2) {
        int length = array1.length + array2.length;
        T[] result = Arrays.copyOf(array1, length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }
    public static <T> T commonElements(T arr1, T arr2) {
        T[] wrapperArr1 = Converter.toWrapperArray(arr1);
        T[] wrapperArr2 = Converter.toWrapperArray(arr2);
        return Converter.toPrimitiveArray(commonElements(wrapperArr1, wrapperArr2));
    }
    public static <T> T[] commonElements(T[] arr1, T[] arr2) {
        if (arr1.length == 0 || arr2.length == 0) {
            return (T[]) Array.newInstance(arr1.getClass().getComponentType(), 0);
        }
        List<T> commonElements = new ArrayList<>();
        boolean[] taken = new boolean[arr2.length];
        for (T t : arr1) {
            for (int i = 0; i < arr2.length; i++) {
                if (taken[i]) continue;
                if (t.equals(arr2[i])) {
                    commonElements.add(t);
                    taken[i] = true;
                    break;
                }
            }
        }
        return Converter.toWrapperArray(Converter.listToArr(commonElements, arr1.getClass().getComponentType()));
    }
}
