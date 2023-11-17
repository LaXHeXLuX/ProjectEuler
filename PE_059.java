import UsefulFunctions.ArrayFunctions;
import UsefulFunctions.Converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_059 {
    public static void main(String[] args) throws IOException {

        // lowercase letters at 97-122 included

        String filename = "PE_059_cipher.txt";
        int[] chars = parse(filename);

        decode(chars);

        int[] key = {'e', 'x', 'p'};
        int[] answer = XOR(chars, key);
        System.out.println(sum(answer));
    }

    private static int sum(int[] arr) {
        int sum = 0;
        for (int a : arr) sum += a;
        return sum;
    }

    private static void decode(int[] chars) {
        int[][] likelyKeys = findLikelyKeys(chars);
        System.out.println("Found keys: " + Arrays.deepToString(likelyKeys));
        for (int i = 0; i < likelyKeys[0].length; i++) {
            if (likelyKeys[0][i] < 97 || likelyKeys[0][i] > 122) continue;
            System.out.println();
            for (int j = 0; j < likelyKeys[1].length; j++) {
                if (likelyKeys[1][j] < 97 || likelyKeys[1][j] > 122) continue;
                for (int k = 0; k < likelyKeys[2].length; k++) {
                    if (likelyKeys[2][k] < 97 || likelyKeys[2][k] > 122) continue;
                    int[] key = {likelyKeys[0][i], likelyKeys[1][j], likelyKeys[2][k]};
                    int[] decodedChars = XOR(chars, key);
                    System.out.println(asciiToString(key) + ": " + asciiToString(decodedChars));
                }
            }
        }
    }

    private static int[] parse(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine();
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
        List<List<Integer>> allArrs = new ArrayList<>();
        allArrs.add(arr1);
        allArrs.add(arr2);
        allArrs.add(arr3);

        for (int i = 0; i < chars.length; i++) {
            allArrs.get(i%3).add(chars[i]);
        }

        int[] chars1 = Converter.listToArrInt(allArrs.get(0));
        int[] chars2 = Converter.listToArrInt(allArrs.get(1));
        int[] chars3 = Converter.listToArrInt(allArrs.get(2));
        return new int[][] {chars1, chars2, chars3};
    }

    private static int[][] findLikelyKeys(int[] chars) {
        int[][] threeSets = divideIntoThree(chars);
        System.out.println("3S: " + Arrays.deepToString(threeSets));
        int[][] likelyKeys = new int[3][3];
        int mostLikelyChar = 'e';
        for (int i = 0; i < threeSets.length; i++) {
            threeSets[i] = ArrayFunctions.mergeSort(threeSets[i]);
            int[] commonElements = nMostCommonElements(threeSets[i]);
            System.out.println(i + ": " + Arrays.toString(commonElements));
            likelyKeys[i] = XOR(commonElements, new int[] {mostLikelyChar});
        }

        return likelyKeys;
    }

    private static int[] nMostCommonElements(int[] arr) {
        int[] arrClone = ArrayFunctions.mergeSort(arr);
        int[] nMostCommonElements = new int[3];
        int[] numberOfMostCommonElements = new int[3];
        Arrays.fill(numberOfMostCommonElements, -1);
        Arrays.fill(nMostCommonElements, -1);

        int currentStreak = 1;
        for (int i = 1; i < arrClone.length; i++) {
            if (arrClone[i] == arrClone[i-1]) {
                currentStreak++;
                continue;
            }
            int smallestIndex = 0;
            int smallest = numberOfMostCommonElements[0];
            for (int j = 1; j < numberOfMostCommonElements.length; j++) {
                if (numberOfMostCommonElements[j] < smallest) {
                    smallest = numberOfMostCommonElements[j];
                    smallestIndex = j;
                }
            }
            if (currentStreak > numberOfMostCommonElements[smallestIndex]) {
                numberOfMostCommonElements[smallestIndex] = currentStreak;
                nMostCommonElements = ArrayFunctions.switchElement(nMostCommonElements, nMostCommonElements[smallestIndex], arrClone[i-1]);
            }
            currentStreak = 1;
        }

        return nMostCommonElements;
    }

    private static String asciiToString(int[] chars) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int c : chars) {
            stringBuilder.append((char) c);
        }

        return stringBuilder.toString();
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
