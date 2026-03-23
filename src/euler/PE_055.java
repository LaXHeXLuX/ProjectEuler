package euler;

import utils.Converter;

import java.util.ArrayList;
import java.util.List;

public class PE_055 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 10_000;
        int[] answer = lychrelNumbersUnder(limit);
        return String.valueOf(answer.length);
    }

    private static int[] lychrelNumbersUnder(int limit) {
        List<Integer> lychrelNumbers = new ArrayList<>();

        for (int i = 5; i < limit; i++) {
            if (iterationsToProducePalindrome(i) < 0) lychrelNumbers.add(i);
        }

        return Converter.listToArr(lychrelNumbers);
    }

    private static int iterationsToProducePalindrome(long n) {
        n += reverse(n);
        long r = reverse(n);
        int counter = 1;

        while (r != n) {
            n += r;
            r = reverse(n);
            if (n < 0) return -1;
            counter++;
            if (counter > 50) return -1;
        }
        return counter;
    }

    private static long reverse(long n) {
        long r = 0;
        while (n > 0) {
            r = 10*r + n%10;
            n /= 10;
        }
        return r;
    }
}
