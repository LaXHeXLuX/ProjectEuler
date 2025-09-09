import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class PE_042 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        String file = "PE_042_words.txt";
        return countTriangleWords(parse(file));
    }

    private static int getWordValue(String word) {
        int sum = 0;
        int asciiConstant = 64;

        for (int i = 0; i < word.length(); i++) {
            sum += word.charAt(i) - asciiConstant;
        }

        return sum;
    }

    private static boolean[] triangleNumbers(int limit) {
        boolean[] triangleNumbers = new boolean[limit];
        Arrays.fill(triangleNumbers, false);

        int n = 1;
        int i = 2;
        while (n < limit) {
            triangleNumbers[n] = true;
            n += i;
            i++;
        }

        return triangleNumbers;
    }

    private static boolean isTriangleWord(String word, boolean[] triangleNumbers) {
        int value = getWordValue(word);
        return triangleNumbers[value];
    }

    private static String[] parse(String filename) {
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            line = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] words = line.split(",");

        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("\"", "");
        }

        return words;
    }

    private static int countTriangleWords(String[] words) {
        int counter = 0;
        int valueLimit = 10_000;
        boolean[] triangleNumbers = triangleNumbers(valueLimit);

        for (String word : words) {
            if (isTriangleWord(word, triangleNumbers)) counter++;
        }

        return counter;
    }
}
