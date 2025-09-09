import util.Combinations;
import util.Converter;

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
        int[] digits = Converter.digitArray(factorial);
        int sumOfDigits = 0;
        for (int digit : digits) sumOfDigits += digit;
        return sumOfDigits;
    }
}
