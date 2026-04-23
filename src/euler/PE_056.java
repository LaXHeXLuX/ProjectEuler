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
        int maxDigits = (int) (bLimit * Math.log10(aLimit));
        double avg = 4.5 + 1.0/Math.pow(maxDigits, 0.25);
        int maximumDigitalSum = 0;

        for (int a = aLimit-1; a > 0; a--) {
            int bMin = (int) ((maximumDigitalSum / avg) / Math.log10(a));
            for (int b = bLimit-1; b > bMin; b--) {
                BigInteger power = BigInteger.valueOf(a).pow(b);
                int digitSum = Diophantine.digitSum(power);
                if (digitSum > maximumDigitalSum) maximumDigitalSum = digitSum;
            }
        }

        return maximumDigitalSum;
    }
}
