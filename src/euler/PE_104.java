package euler;

import java.math.BigInteger;
import java.util.*;

public class PE_104 {
    private static final BigInteger[][] pow2Fibo = new BigInteger[22][];
    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
       makePow2Fibo();
        for (int i = 0; i < 5; i++) {
            System.out.println(i + ": " + Arrays.toString(pow2Fibo[i]));
        }
        System.out.println("fibo done");
        return firstFibonacciWithProperty();
    }

    private static void makePow2Fibo() {
        BigInteger[] m = {BigInteger.ONE, BigInteger.ONE, BigInteger.ZERO};
        pow2Fibo[0] = m;
        for (int i = 1; i < pow2Fibo.length; i++) {
            m = square(m);
            pow2Fibo[i] = m;
        }
    }

    private static BigInteger[] pow(int n) {
        BigInteger[] m = {BigInteger.ONE, BigInteger.ZERO, BigInteger.ONE};
        for (int i = 0; i < 32 - Integer.numberOfLeadingZeros(n); i++) {
            if ((n & (1 << i)) != 0) multiply(m, pow2Fibo[i]);
        }
        return m;
    }

    private static BigInteger[] square(BigInteger[] m) {
        return new BigInteger[] {
                m[0].multiply(m[0]).add(m[1].multiply(m[1])),
                m[1].multiply(m[0]).add(m[2].multiply(m[1])),
                m[1].multiply(m[1]).add(m[2].multiply(m[2]))
        };
    }

    private static void multiply(BigInteger[] m1, BigInteger[] m2) {
        BigInteger t0 = m1[0].multiply(m2[0]).add(m1[1].multiply(m2[1]));
        BigInteger t1 = m1[0].multiply(m2[1]).add(m1[1].multiply(m2[2]));
        BigInteger t2 = m1[1].multiply(m2[1]).add(m1[2].multiply(m2[2]));
        m1[0] = t0; m1[1] = t1; m1[2] = t2;
    }

    private static int firstFibonacciWithProperty() {
        int f1 = 1;
        int f2 = 1;
        int counter = 2;
        while (true) {
            int temp = f2;
            f2 = (f2 + f1) % 1_000_000_000;
            f1 = temp;
            counter++;
            if (isPandigitalBack(f2)) {
                BigInteger fn = pow(counter)[1];
                if (isPandigitalFront(fn.toString())) return counter;
            }
        }
    }

    private static boolean isPandigitalBack(int n) {
        if (n < 123456789) return false;
        boolean[] digits = new boolean[9];
        while (n > 0) {
            int digit = n % 10;
            if (digit == 0 || digits[digit-1]) return false;
            digits[digit-1] = true;
            n /= 10;
        }
        return true;
    }

    private static boolean isPandigitalFront(String s) {
        if (s.length() < 100) return false;
        boolean[] digits = new boolean[9];
        for (int i = 0; i < 9; i++) {
            int digit = s.charAt(i) - '0';
            if (digit == 0 || digits[digit-1]) return false;
            digits[digit-1] = true;
        }
        return true;
    }
}
