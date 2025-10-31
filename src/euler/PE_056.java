package euler;

import java.math.BigInteger;

public class PE_056 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 100;
        int[] digitSum = biggestDigitsSumOfPower(limit, limit);
        return digitSum[2];
    }

    private static int[] biggestDigitsSumOfPower(int limitA, int limitB) {
        int[] biggests = new int[3];
        biggests[2] = -1;

        for (int a = limitA; a > 0; a--) {
            for (int b = limitB; b > 0; b--) {
                BigInteger power = power(a, b);
                int digitSum = digitSum(power);
                if (digitSum > biggests[2]) {
                    biggests[0] = a;
                    biggests[1] = b;
                    biggests[2] = digitSum;
                }
            }
        }

        return biggests;
    }

    private static BigInteger power(int a, int b) {
        BigInteger answer = BigInteger.valueOf(a);
        b--;

        for (int i = 0; i < b; i++) {
            answer = answer.multiply(BigInteger.valueOf(a));
        }

        return answer;
    }

    private static int digitSum(BigInteger n) {
        int sum = 0;
        while (n.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divRem = n.divideAndRemainder(BigInteger.TEN);
            sum += divRem[1].intValue();
            n = divRem[0];
        }
        return sum;
    }
}
