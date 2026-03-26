package euler;

import utils.Converter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PE_063 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        BigInteger[] solutions = solve();
        return String.valueOf(solutions.length);
    }

    private static BigInteger[] solve() {
        List<BigInteger> solutions = new ArrayList<>();
        for (int i = 1; i < 10; i++) solutions.add(BigInteger.valueOf(i));
        int aLimit = 9;

        for (int a = 1; a <= aLimit; a++) {
            int b = 2;
            BigInteger power = power(a, b);
            int digits = power.toString().length();
            while (digits >= b) {
                if (digits == b) {
                    solutions.add(power);
                }
                b++;
                power = power(a, b);
                digits = power.toString().length();
            }
        }
        return Converter.listToArr(solutions);
    }

    private static BigInteger power(int a, int b) {
        BigInteger bigA = BigInteger.valueOf(a);
        return bigA.pow(b);
    }
}
