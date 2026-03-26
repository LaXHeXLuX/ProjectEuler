package euler;

import utils.Parser;

import java.util.HashMap;
import java.util.Map;

public class PE_059 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        String filename = "src/euler/inputs/PE_059_cipher.txt";
        int[] chars = parse(filename);

        int[] key = likelyKey(chars);
        int[] answer = XOR(chars, key);
        return String.valueOf(sum(answer));
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
        int[][] charsDivided = new int[3][];
        for (int i = 0; i < 3; i++) {
            int l = chars.length/3;
            if (chars.length % 3 > i) l++;
            charsDivided[i] = new int[l];
        }
        for (int i = 0; i < chars.length; i++) {
            charsDivided[i%3][i/3] = chars[i];
        }

        return charsDivided;
    }

    private static int[] likelyKey(int[] chars) {
        int[][] threeSets = divideIntoThree(chars);
        int[] likelyKey = new int[3];
        for (int i = 0; i < threeSets.length; i++) {
            likelyKey[i] = mostCommonElement(threeSets[i]) ^ (int) ' ';
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
            output[i] = input[i] ^ key[keyIndex];
        }

        return output;
    }
}
