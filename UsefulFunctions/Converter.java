package UsefulFunctions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static long[] listToArrLong(List<Long> list) {
        long[] arr = new long[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    public static int[] listToArrInt(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    public static long[][] arrListToArrLong(List<long[]> list) {
        long[][] arr = new long[list.size()][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    public static int[][] arrListToArrInt(List<int[]> list) {
        int[][] arr = new int[list.size()][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    public static int[] reverse(int[] arr) {
        int[] reversed = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int index = reversed.length-i-1;
            reversed[index] = arr[i];
        }
        return reversed;
    }
    public static int[] digitArray(long n) {
        List<Integer> digitArray = new ArrayList<>();
        while (n > 0) {
            digitArray.add((int) (n % 10));
            n /= 10;
        }
        return reverse(listToArrInt(digitArray));
    }
    public static int[] digitArray(BigInteger n) {
        List<Integer> digitArray = new ArrayList<>();
        while (n.compareTo(BigInteger.ZERO) != 0) {
            digitArray.add(n.remainder(BigInteger.TEN).intValue());
            n = n.divide(BigInteger.TEN);
        }
        return reverse(listToArrInt(digitArray));
    }
    public static long digitFromArrayLong(int[] digitArray) {
        long n = 0;
        for (int digit : digitArray) n = 10*n + digit;
        return n;
    }
    public static BigInteger digitFromArrayBigInteger(int[] digitArray) {
        BigInteger n = BigInteger.ZERO;
        for (int digit : digitArray) n = n.multiply(BigInteger.TEN).add(BigInteger.valueOf(digit));
        return n;
    }
    public static int[] convertToBase(long n, int base) {
        if (n == 0) return new int[] {0};

        int[] convertedDigits = new int[(int)(Math.log(n)/Math.log(base))+1];
        for (int i = 0; i < convertedDigits.length; i++) {
            convertedDigits[convertedDigits.length-i-1] = (int) (n%base);
            n /= base;
        }

        return convertedDigits;
    }

    public static int[] booleanArrToIntArr(boolean[] arr) {
        int counter = 0;
        for (boolean value : arr) if (value) counter++;

        int[] newArr = new int[counter];

        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]) {
                newArr[index] = i;
                index++;
            }
        }

        return newArr;
    }

}
