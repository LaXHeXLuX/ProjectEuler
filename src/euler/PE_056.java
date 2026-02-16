package euler;

import utils.Diophantine;

import java.math.BigInteger;

public class PE_056 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 100;
        int[] digitSum = biggestDigitsSumOfPower(limit, limit);
        return digitSum[2];
    }

    private static int[] biggestDigitsSumOfPower(int limitA, int limitB) {
        int[] biggests = new int[3];

        for (int a = limitA; a > 0; a--) {
            int lowerLimitB = (int) ((biggests[2] / 9.0) / Math.log10(a));
            for (int b = limitB; b > lowerLimitB; b--) {
                BigInteger power = BigInteger.valueOf(a).pow(b);
                int digitSum = Diophantine.digitSum(power);
                if (digitSum > biggests[2]) {
                    biggests[0] = a;
                    biggests[1] = b;
                    biggests[2] = digitSum;
                }
            }
        }

        return biggests;
    }
}
