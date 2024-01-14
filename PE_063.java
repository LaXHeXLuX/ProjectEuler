import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_063 {
    public static void main(String[] args) {
        BigInteger[] solutions = solve();
        System.out.println(Arrays.toString(solutions));
        System.out.println(solutions.length);
    }

    private static BigInteger[] solve() {
        List<BigInteger> solutions = new ArrayList<>();
        int aLimit = 9;

        for (int a = 1; a <= aLimit; a++) {
            int b = 1;
            BigInteger power = power(a, b);
            int[] digits = Converter.digitArray(power);
            while (digits.length >= b) {
                if (digits.length == b) {
                    solutions.add(power);
                }
                b++;
                power = power(a, b);
                digits = Converter.digitArray(power);
            }
        }
        return Converter.listToArr(solutions);
    }

    private static BigInteger power(int a, int b) {
        BigInteger bigA = BigInteger.valueOf(a);
        return bigA.pow(b);
    }
}
