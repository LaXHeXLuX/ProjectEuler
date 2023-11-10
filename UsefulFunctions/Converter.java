package UsefulFunctions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Converter {
    public long[] listToArrLong(List<Long> list) {
        long[] arr = new long[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    public int[] listToArrInt(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    public long[][] arrListToArrLong(List<long[]> list) {
        long[][] arr = new long[list.size()][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    public int[][] arrListToArrInt(List<int[]> list) {
        int[][] arr = new int[list.size()][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    public int[] reverse(int[] arr) {
        int[] reversed = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int index = reversed.length-i-1;
            reversed[index] = arr[i];
        }
        return reversed;
    }
    public int[] digitArray(long n) {
        List<Integer> digitArray = new ArrayList<>();
        while (n > 0) {
            digitArray.add((int) (n % 10));
            n /= 10;
        }
        return reverse(listToArrInt(digitArray));
    }
    public int[] digitArray(BigInteger n) {
        List<Integer> digitArray = new ArrayList<>();
        while (n.compareTo(BigInteger.ZERO) != 0) {
            digitArray.add(n.remainder(BigInteger.TEN).intValue());
            n = n.divide(BigInteger.TEN);
        }
        return reverse(listToArrInt(digitArray));
    }
}
