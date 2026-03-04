package euler;

import utils.Diophantine;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PE_206 {
    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }

    public static String PE() {
        int[] form = {1, -1, 2, -1, 3, -1, 4, -1, 5, -1, 6, -1, 7, -1, 8, -1, 9, -1, 0};
        List<Long> squaresWithForm = squaresWithForm(form);
        return String.valueOf(squaresWithForm.getFirst());
    }

    private static List<Long> squaresWithForm(int[] form) {
        List<Long> validNumbers = new ArrayList<>();
        validNumbers.add(0L);
        long max = BigInteger.TEN.pow(form.length).sqrt().longValue();
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
        long powTen = Diophantine.pow(10, lastSize);
        int adding = place + 1 - lastSize;

        List<Long> validNumbers = new ArrayList<>();
        long limit = Diophantine.pow(10, adding);
        for (long i = 0; i < limit; i++) {
            if (i*powTen + currentNumbers.getFirst() >= max) break;
            for (Long currentNumber : currentNumbers) {
                long n = i*powTen + currentNumber;
                if (n >= max) break;
                if (squareHasNthDigitTarget(n, place, target)) {
                    validNumbers.add(n);
                }
            }
        }
        return validNumbers;
    }

    private static boolean squareHasNthDigitTarget(long sq, int n, int target) {
        BigInteger square = BigInteger.valueOf(sq).pow(2);
        int nthDigit = square.divide(BigInteger.TEN.pow(n)).mod(BigInteger.TEN).intValue();
        return nthDigit == target;
    }
}
