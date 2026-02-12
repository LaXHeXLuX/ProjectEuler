package euler;

import utils.Diophantine;

public class PE_145 {

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int digits = 9;
        return reversibleNumbersWithUpToNDigits(digits);
    }

    private static long reversibleNumbersWithNDigits(int n) {
        if (n % 2 == 0) return 20 * Diophantine.pow(30, n/2 - 1);
        if (n % 4 == 3) return 100 * Diophantine.pow(500, n / 4);
        return 0;
    }

    private static long reversibleNumbersWithUpToNDigits(int n) {
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += reversibleNumbersWithNDigits(i);
        }
        return sum;
    }
}