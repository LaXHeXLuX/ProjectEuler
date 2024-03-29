import java.math.BigInteger;
import java.util.Arrays;

public class PE_056 {
    public static void main(String[] args) {
        int limit = 100;
        System.out.println(Arrays.toString(biggestDigitsSumOfPower(limit, limit)));
    }

    private static int[] biggestDigitsSumOfPower(int limitA, int limitB) {
        int[] biggests = new int[3];
        biggests[2] = -1;

        for (int a = 0; a < limitA; a++) {
            for (int b = 0; b < limitB; b++) {
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
        int[] digits = Converter.digitArray(n);
        int sum = 0;

        for (int digit : digits) sum += digit;

        return sum;
    }
}
