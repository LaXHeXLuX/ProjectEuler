package euler;

import java.math.BigInteger;

public class PE_048 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1000;
        BigInteger rem = BigInteger.valueOf ((long) Math.pow(10, 10));
        BigInteger answer = selfPowersSum(limit, rem);
        return answer.remainder(BigInteger.valueOf(10_000_000_000L)).longValue();
    }

    private static BigInteger selfPowersSum(int limit, BigInteger rem) {
        BigInteger sum = BigInteger.ZERO;

        for (int i = 1; i <= limit; i++) {
            BigInteger n = BigInteger.valueOf(i);
            sum = sum.add(n.modPow(n, rem)).mod(rem);
        }

        return sum;
    }
}
