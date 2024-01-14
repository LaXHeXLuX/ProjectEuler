import java.math.BigInteger;
public class PE_020 {
    public static void main(String[] args) {
        int n = 100;
        System.out.println(sumOfDigitsOfFactorial(n));
    }
    public static int sumOfDigitsOfFactorial(int n) {
        BigInteger factorial = Combinations.factorialBigInteger(n);
        int[] digits = Converter.digitArray(factorial);
        int sumOfDigits = 0;
        for (int digit : digits) sumOfDigits += digit;
        return sumOfDigits;
    }
}
