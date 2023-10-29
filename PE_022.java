import java.io.*;
import java.util.Arrays;

public class PE_022 {
    public static void main(String[] args) throws IOException {
        String filename = "PE_022_names.txt";
        String[] names = parse(filename);
        names = mergeSort(names);
        System.out.println(Arrays.toString(names));
        System.out.println(sumOfAllScores(names));
    }
    private static String[] parse(String filename) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        String names = bf.readLine();
        names = names.replaceAll("\"", "");
        return names.split(",");
    }
    private static String[] mergeSort(String[] arr) {
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
    private static int nameValue(String name) {
        int valueConstant = -64;
        int sum = 0;
        for (int i = 0; i < name.length(); i++) {
            int value = (int) name.charAt(i) + valueConstant;
            sum += value;
        }
        return sum;
    }
    private static int sumOfAllScores(String[] words) {
        int sum = 0;
        for (int i = 0; i < words.length; i++) {
            int score = nameValue(words[i]) * (i+1);
            sum += score;
        }
        return sum;
    }
}
