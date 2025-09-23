import util.Converter;
import util.Parser;

import java.util.*;

public class PE_059 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        String filename = "inputs/PE_059_cipher.txt";
        int[] chars = parse(filename);

        int[] key = findLikelyKey(chars);
        int[] answer = XOR(chars, key);
        return sum(answer);
    }

    private static int sum(int[] arr) {
        int sum = 0;
        for (int a : arr) sum += a;
        return sum;
    }

    private static int[] parse(String filename) {
        String line = Parser.parseStrings(filename)[0];
        String[] numbers = line.split(",");
        int[] numbersInt = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            numbersInt[i] = Integer.parseInt(numbers[i]);
        }

        return numbersInt;
    }

    private static int[][] divideIntoThree(int[] chars) {
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        List<Integer> arr3 = new ArrayList<>();
        List<List<Integer>> allLists = new ArrayList<>();
        allLists.add(arr1);
        allLists.add(arr2);
        allLists.add(arr3);

        for (int i = 0; i < chars.length; i++) {
            allLists.get(i%3).add(chars[i]);
        }

        int[] chars1 = Converter.listToArr(allLists.get(0));
        int[] chars2 = Converter.listToArr(allLists.get(1));
        int[] chars3 = Converter.listToArr(allLists.get(2));
        return new int[][] {chars1, chars2, chars3};
    }

    private static int[] findLikelyKey(int[] chars) {
        int[][] threeSets = divideIntoThree(chars);
        int[] likelyKey = new int[3];
        for (int i = 0; i < threeSets.length; i++) {
            likelyKey[i] = XOR(mostCommonElement(threeSets[i]), ' ');
        }

        return likelyKey;
    }

    private static int mostCommonElement(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int mostCommon = arr[0];
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
            if (freqMap.get(num) > freqMap.get(mostCommon)) mostCommon = num;
        }
        return mostCommon;
    }

    private static int[] XOR(int[] input, int[] key) {
        int[] output = new int[input.length];

        for (int i = 0; i < output.length; i++) {
            int keyIndex = i % key.length;
            output[i] = XOR(input[i], key[keyIndex]);
        }

        return output;
    }

    private static int XOR(int input, int key) {
        boolean[] inputBase2 = Converter.booleanConversion(Converter.convertToBase(input, 2));
        boolean[] keyBase2 = Converter.booleanConversion(Converter.convertToBase(key, 2));
        boolean[] outputBase2 = XOR(inputBase2, keyBase2);
        return (int) Converter.convertFromBase(Converter.booleanConversion(outputBase2), 2);
    }

    private static boolean[] XOR(boolean[] input, boolean[] key) {
        if (input.length != key.length) {
            boolean[][] inputs = assertSameSize(input, key);
            input = inputs[0];
            key = inputs[1];
        }
        boolean[] output = new boolean[input.length];

        for (int i = output.length-1; i >= 0; i--) {
            int keyIndex = i % key.length;
            output[i] = XOR(input[i], key[keyIndex]);
        }

        return output;
    }

    private static boolean XOR(boolean a, boolean b) {
        return (!a && b) || (a && !b);
    }

    private static boolean[][] assertSameSize(boolean[] arr1, boolean[] arr2) {
        if (arr1.length == arr2.length) return new boolean[][] {arr1, arr2};
        if (arr1.length > arr2.length) {
            arr2 = addZeroesToFront(arr2, arr1.length);
            return new boolean[][] {arr1, arr2};
        }
        arr1 = addZeroesToFront(arr1, arr2.length);
        return new boolean[][] {arr1, arr2};
    }

    private static boolean[] addZeroesToFront(boolean[] arr, int size) {
        boolean[] newArr = new boolean[size];
        System.arraycopy(arr, 0, newArr, newArr.length-arr.length, arr.length);
        return newArr;
    }
}
