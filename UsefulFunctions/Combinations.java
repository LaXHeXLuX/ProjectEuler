package UsefulFunctions;

import java.math.BigInteger;

public class Combinations {

    public static BigInteger factorialBigInteger(int n) {
        if (n < 0) return BigInteger.ZERO;
        if (n < 2) return BigInteger.ONE;
        BigInteger factorial = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
    public static long factorialLong(int n) {
        if (n < 0) return 0L;
        if (n < 2) return 1L;
        long factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }
    public static BigInteger nChooseMBigInteger(int n, int m) {
        BigInteger factorial1 = factorialBigInteger(n);
        BigInteger factorial2 = factorialBigInteger(m);
        BigInteger factorial3 = factorialBigInteger(n-m);
        return factorial1.divide(factorial2.multiply(factorial3));
    }
}
