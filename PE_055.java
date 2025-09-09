import util.Converter;

import java.util.ArrayList;
import java.util.List;

public class PE_055 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 10_000;
        int[] answer = findLychrelNumbersUnder(limit);
        return answer.length;
    }

    public static int[] findLychrelNumbersUnder(int limit) {
        List<Integer> lychrelNumbers = new ArrayList<>();

        for (int i = 5; i < limit; i++) {
            if (iterationsToProducePalindrome(i) < 0) lychrelNumbers.add(i);
        }

        return Converter.listToArr(lychrelNumbers);
    }

    public static int iterationsToProducePalindrome(long n) {
        n += reverse(n);
        int counter = 1;

        while (counter <= 50 && !isPalindrome(n)) {
            n += reverse(n);
            if (n < 0) return -1;
            counter++;
        }

        if (counter > 50) return -1;
        return counter;
    }

    private static long reverse(long n) {
        String number = String.valueOf(n);
        String reversed = String.valueOf(new StringBuilder(number).reverse());
        return Long.parseLong(reversed);
    }

    private static boolean isPalindrome(long n) { // note for the future: doing it with string isn't that much faster, maybe like 1.5x faster for bigger numbers
        String string = String.valueOf(n);
        return (string.contentEquals(new StringBuilder(string).reverse()));
    }
}
