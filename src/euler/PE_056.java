package euler;

import utils.Diophantine;

import java.math.BigInteger;

public class PE_056 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 100;
        return String.valueOf(maximumDigitalSum(limit, limit));
    }

    private static int maximumDigitalSum(int aLimit, int bLimit) {
        int maximumDigitalSum = 0;

        for (int a = aLimit-1; a > 0; a--) {
            int lowerLimitB = (int) ((maximumDigitalSum / 5.0) / Math.log10(a));
            for (int b = bLimit-1; b > lowerLimitB; b--) {
                BigInteger power = BigInteger.valueOf(a).pow(b);
                int digitSum = Diophantine.digitSum(power);
                if (digitSum > maximumDigitalSum) maximumDigitalSum = digitSum;
            }
        }

        return maximumDigitalSum;
    }
}
