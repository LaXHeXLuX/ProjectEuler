import UsefulFunctions.Converter;

import java.math.BigInteger;

public class PE_016 {
    public static void main(String[] args) {
        int n = 2;
        int power = 1000;
        System.out.println(sumOfDigits(power(n ,power)));
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
        Converter c = new Converter();
        int[] digitArray = c.digitArray(n);
        for (int digit : digitArray) {
            sum += digit;
        }
        return sum;
    }

}
