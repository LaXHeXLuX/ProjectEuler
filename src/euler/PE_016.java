package euler;

import utils.Diophantine;

import java.math.BigInteger;

public class PE_016 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 2;
        int p = 1000;
        return Diophantine.digitSum(power(n, p));
    }

    private static BigInteger power(int n, int power) {
        BigInteger startingNumber = BigInteger.valueOf(n);
        BigInteger answer = startingNumber;
        for (int i = 1; i < power; i++) {
            answer = answer.multiply(startingNumber);
        }
        return answer;
    }
}
