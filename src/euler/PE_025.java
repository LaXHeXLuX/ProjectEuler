package euler;

import java.math.BigInteger;

public class PE_025 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 1000;
        return String.valueOf(firstFibonacciWithNDigits(n));
    }

    private static long firstFibonacciWithNDigits(int n) {
        BigInteger fibo1 = BigInteger.ONE;
        BigInteger fibo2 = BigInteger.ONE;
        int indexOfFibo2 = 2;
        int diff = n - numberOfDigits(fibo2);
        while (diff > 0) {
            for (int i = 0; i < diff; i++) {
                BigInteger temp = fibo2;
                fibo2 = fibo2.add(fibo1);
                fibo1 = temp;
                indexOfFibo2++;
            }
            diff = n - numberOfDigits(fibo2);
        }
        return indexOfFibo2;
    }

    private static int numberOfDigits(BigInteger n) {
        int estimate = (int) (n.bitLength() * Math.log10(2)) + 1;
        if (BigInteger.TEN.pow(estimate - 1).compareTo(n) > 0) estimate--;
        return estimate;
    }
}
