package euler;

import java.math.BigInteger;

public class PE_025 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 1000;
        return firstFibonacciWithNDigits(n);
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
        if (n.compareTo(BigInteger.ZERO) == 0) return 0;
        int digitCount = 1;
        while(n.compareTo(BigInteger.TEN) >= 0) {
            digitCount++;
            n = n.divide(BigInteger.TEN);
        }
        return digitCount;
    }
}
