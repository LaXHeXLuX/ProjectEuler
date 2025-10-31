package src.euler;

import util.Combinations;

import java.math.BigInteger;

public class PE_020 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 100;
        return sumOfDigitsOfFactorial(n);
    }

    public static int sumOfDigitsOfFactorial(int n) {
        BigInteger factorial = Combinations.factorialBigInteger(n);
        int sumOfDigits = 0;
        while (factorial.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divRem = factorial.divideAndRemainder(BigInteger.TEN);
            sumOfDigits += divRem[1].intValue();
            factorial = divRem[0];
        }
        return sumOfDigits;
    }
}
