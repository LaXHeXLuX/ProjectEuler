package euler;

import utils.Converter;

import java.math.BigInteger;

public class PE_016 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 2;
        int p = 1000;
        return sumOfDigits(power(n, p));
    }

    private static BigInteger power(int n, int power) {
        BigInteger startingNumber = BigInteger.valueOf(n);
        BigInteger answer = startingNumber;
        for (int i = 1; i < power; i++) {
            answer = answer.multiply(startingNumber);
        }
        return answer;
    }

    private static long sumOfDigits(BigInteger n) {
        long sum = 0;
        int[] digitArray = Converter.digitArray(n);
        for (int digit : digitArray) {
            sum += digit;
        }
        return sum;
    }
}
