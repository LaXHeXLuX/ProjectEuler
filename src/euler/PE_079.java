package euler;

import utils.ArrayFunctions;
import utils.Converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PE_079 {
    public static void main(String[] args) { // assuming only one character each
        System.out.println(PE());
    }

    public static long PE() {
        String filename = "src/euler/inputs/PE_079_keylog.txt";
        int[] passcodes = parseUnique(filename);

        List<int[]> shortestPasswords = shortestPossiblePassword(passcodes);
        return Converter.fromDigitArray(shortestPasswords.getFirst());
    }

    private static int[] parseUnique(String filename) {
        List<Integer> numbers = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            while (line != null) {
                int number = Integer.parseInt(line);
                if (!numbers.contains(number)) numbers.add(number);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Converter.listToArr(numbers);
    }

    private static List<int[]> shortestPossiblePassword(int[] passcodes) {
        List<int[]> possiblePasswords = new ArrayList<>();
        possiblePasswords.add(Converter.digitArray(passcodes[0]));

        for (int i = 1; i < passcodes.length; i++) {
            int[] passcode = Converter.digitArray(passcodes[i]);

            List<int[]> newPossiblePasswords = new ArrayList<>();
            for (int[] possiblePassword : possiblePasswords) {
                newPossiblePasswords.addAll(newPasswords(possiblePassword, passcode));
            }

            possiblePasswords = newPossiblePasswords;
        }

        return getOnlyShortest(possiblePasswords);
    }

    private static List<int[]> getOnlyShortest(List<int[]> list) {
        List<int[]> shortest = new ArrayList<>();
        int length = 0;

        for (int[] arr : list) {
            if (arr.length < length) continue;
            if (arr.length == length) {
                shortest.add(arr);
                continue;
            }
            shortest = new ArrayList<>();
            shortest.add(arr);
            length = arr.length;
        }

        return shortest;
    }

    private static List<int[]> newPasswords(int[] currentPassword, int[] newPasscode) {
        return newPasswords(currentPassword, newPasscode, 0);
    }

    private static List<int[]> newPasswords(int[] currentPassword, int[] passcode, int allowedStart) {
        List<int[]> newPasswords = new ArrayList<>();

        if (passcode.length == 0) {
            newPasswords.add(currentPassword);
            return newPasswords;
        }

        if (currentPassword.length < allowedStart) {
            return newPasswords;
        }

        int[] newPasscode = ArrayFunctions.removeIndex(passcode, 0);

        for (int i = 0; i < currentPassword.length; i++) {
            if (currentPassword[i] == passcode[0]) {
                if (i < allowedStart) return newPasswords;
                else return newPasswords(currentPassword, newPasscode, i+1);
            }
        }

        for (int i = allowedStart; i <= currentPassword.length; i++) {
            int[] newPassword = inject(currentPassword, passcode[0], i);
            newPasswords.addAll(newPasswords(newPassword, newPasscode, i+1));
        }

        return newPasswords;
    }

    private static int[] inject(int[] arr, int el, int index) {
        int[] newArr = new int[arr.length+1];
        System.arraycopy(arr, 0, newArr, 0, index);
        newArr[index] = el;
        System.arraycopy(arr, index, newArr, index+1, arr.length-index);
        return newArr;
    }
}
