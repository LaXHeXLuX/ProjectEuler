package euler;

import utils.Diophantine;

import java.util.ArrayList;
import java.util.List;

public class PE_206 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int[] form = {1, -1, 2, -1, 3, -1, 4, -1, 5, -1, 6, -1, 7, -1, 8, -1, 9, -1, 0};
        List<Long> squaresWithForm = squaresWithForm(form);
        return String.valueOf(squaresWithForm.getFirst());
    }

    private static List<Long> squaresWithForm(int[] form) {
        List<Long> validNumbers = new ArrayList<>();
        validNumbers.add(0L);
        long max = (long) Math.pow(10, form.length / 2.0);
        int lastSize = 0;
        for (int i = form.length-1; i >= 0; i--) {
            if (form[i] == -1) continue;
            validNumbers = validNumbers(validNumbers, form.length-i-1, form[i], lastSize, max);
            lastSize = form.length-i;
            if (validNumbers.isEmpty()) return validNumbers;
        }
        return validNumbers;
    }

    private static List<Long> validNumbers(List<Long> currentNumbers, int place, int target, int lastSize, long max) {
        long powTen = Diophantine.pow10[lastSize];
        long limit = Diophantine.pow10[place + 1 - lastSize];

        List<Long> validNumbers = new ArrayList<>();
        for (long i = 0; i < limit; i++) {
            long offset = i*powTen;
            if (offset + currentNumbers.getFirst() >= max) break;
            for (Long currentNumber : currentNumbers) {
                long n = offset + currentNumber;
                if (n >= max) break;
                if ((n*n / Diophantine.pow10[place]) % 10 == target) {
                    validNumbers.add(n);
                }
            }
        }
        return validNumbers;
    }
}
