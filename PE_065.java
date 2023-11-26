import UsefulFunctions.Converter;

import java.math.BigInteger;
import java.util.Arrays;

public class PE_065 {
    public static void main(String[] args) {
        int n = 100;
        BigInteger[] fraction = computeNthConvergentOfE(n);
        System.out.println(Arrays.toString(fraction));
        int[] digits = Converter.digitArray(fraction[0]);

        int sum = 0;
        for (int digit : digits) sum += digit;
        System.out.println(sum);
    }

    private static BigInteger[] computeNthConvergentOfE(int n) {
        n--;
        if (n == 0) return new BigInteger[] {BigInteger.TWO, BigInteger.ONE};
        BigInteger[] currentFraction = {BigInteger.ONE, BigInteger.valueOf(getNthConvergentTerm(n))};

        for (int i = 1; i < n; i++) {
            currentFraction = addToFraction(currentFraction, getNthConvergentTerm(n-i));
            fractionUpsideDown(currentFraction);
        }

        currentFraction = addToFraction(currentFraction, getNthConvergentTerm(0));
        return currentFraction;
    }

    private static BigInteger[] addToFraction(BigInteger[] currentFraction, int adder) {
        BigInteger[] newFraction = new BigInteger[2];
        newFraction[1] = currentFraction[1];
        newFraction[0] = currentFraction[0].add(BigInteger.valueOf(adder).multiply(newFraction[1]));
        return newFraction;
    }

    private static void fractionUpsideDown(BigInteger[] fraction) {
        BigInteger temp = fraction[0];
        fraction[0] = fraction[1];
        fraction[1] = temp;
    }

    private static int getNthConvergentTerm(int n) {
        if (n == 0) return 2;
        if (n % 3 != 2) return 1;
        return (n/3 + 1)*2;
    }
}
