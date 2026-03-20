package euler.other;

import utils.Converter;
import utils.Diophantine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DigitPowers {
    private static int base;
    private static long[] digitPower;

    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }

    public static String PE() {
        base = 9;
        boolean zeroValueIsOne = true;
        makeDigitPower(zeroValueIsOne);
        List<Long> digitPowers = digitPowers();
        return digitPowers.toString();
    }

    private static void makeDigitPower(boolean zeroValueIsOne) {
        digitPower = new long[base];
        digitPower[0] = 0;
        if (zeroValueIsOne) digitPower[0] = 1;
        digitPower[1] = 1;
        for (int i = 2; i < base; i++) {
            digitPower[i] = Diophantine.pow(i, i);
        }
    }
    
    private static int limit() {
        // (base-1)^(base-1) * x > base^(x-1)
        int x = 1;
        while (digitPower[digitPower.length-1]*x >= Diophantine.pow(base, x-1)) x++;
        return x-1;
    }

    private static long digitPowerSum(long n) {
        long sum = 0;
        while (n > 0) {
            sum += digitPower[(int) (n % base)];
            n /= base;
        }
        return sum;
    }

    private static List<Long> digitPowers() {
        List<Long> digitPowers = new ArrayList<>();

        long limit = Diophantine.pow(base, limit());
        System.out.println("limit: " + limit);
        for (long i = 2; i < limit; i++) {
            if (i % 1_000_000 == 0) System.out.print(i + "\r");
            if (digitPowerSum(i) == i) {
                digitPowers.add(i);
                System.out.println("got " + Arrays.toString(Converter.convertToBase(i, base)));
            }
        }

        return digitPowers;
    }
}
