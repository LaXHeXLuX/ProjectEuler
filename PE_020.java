import UsefulFunctions.Converter;
import UsefulFunctions.Factorial;

import java.math.BigInteger;
public class PE_020 {
    public static void main(String[] args) {
        int n = 100;
        System.out.println(sumOfDigitsOfFactorial(n));
    }
    public static int sumOfDigitsOfFactorial(int n) {
        Factorial f = new Factorial();
        BigInteger factorial = f.factorialBigInteger(n);
        Converter c = new Converter();
        int[] digits = c.digitArray(factorial);
        int sumOfDigits = 0;
        for (int digit : digits) sumOfDigits += digit;
        return sumOfDigits;
    }
}
