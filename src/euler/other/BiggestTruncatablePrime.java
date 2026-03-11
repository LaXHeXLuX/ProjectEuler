package euler.other;

import utils.Primes;

import java.math.BigInteger;

public class BiggestTruncatablePrime {
    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }

    public static String PE() {
        return "L: " + biggestLeftTruncatablePrime() + "\nR: " + biggestRightTruncatablePrime();
    }

    private static BigInteger biggestLeftTruncatablePrime() {
        return biggestLeftTruncatablePrime(BigInteger.ZERO, BigInteger.ONE);
    }

    private static BigInteger biggestLeftTruncatablePrime(BigInteger current, BigInteger powTen) {
        BigInteger biggestPrime = current;
        for (int i = 1; i < 10; i++) {
            BigInteger n = BigInteger.valueOf(i).multiply(powTen).add(current);
            if (!n.isProbablePrime(100)) continue;
            BigInteger biggestTruncated = biggestLeftTruncatablePrime(n, powTen.multiply(BigInteger.TEN));
            if (biggestTruncated.compareTo(biggestPrime) > 0) biggestPrime = biggestTruncated;
        }
        return biggestPrime;
    }

    private static long biggestRightTruncatablePrime() {
        return biggestRightTruncatablePrime(0);
    }

    private static long biggestRightTruncatablePrime(long current) {
        long biggestPrime = current;
        int iStart = 0;
        if (current == 0) iStart = 1;
        for (int i = iStart; i < 10; i++) {
            long n = current*10 + i;
            if (!Primes.isPrime(n)) continue;
            long biggestTruncated = biggestRightTruncatablePrime(n);
            if (biggestTruncated > biggestPrime) biggestPrime = biggestTruncated;
        }
        return biggestPrime;
    }
}
