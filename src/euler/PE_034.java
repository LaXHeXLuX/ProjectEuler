package euler;

import java.util.ArrayList;
import java.util.List;

public class PE_034 {
    private static final int[] FACTORIALS = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        List<Integer> digitFactorials = findAllDigitFactorials();
        int sum = 0;
        for (int digitFactorial : digitFactorials) sum += digitFactorial;
        return sum;
    }

    private static boolean isDigitFactorial(int n) {
        int sum = 0;

        int x = n;
        while (x > 0) {
            sum += FACTORIALS[x % 10];
            x /= 10;
        }

        return sum == n;
    }

    private static List<Integer> findAllDigitFactorials() {
        List<Integer> digitFactorials = new ArrayList<>();
        int limit = 2_000_000;

        for (int i = 10; i < limit; i++) {
            if (isDigitFactorial(i)) digitFactorials.add(i);
        }

        return digitFactorials;
    }
}
