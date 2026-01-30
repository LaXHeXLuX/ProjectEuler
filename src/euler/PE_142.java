package euler;

import utils.Diophantine;
import utils.Divisors;

import java.math.BigInteger;

public class PE_142 {
    private static long smallestSum = Long.MAX_VALUE >> 30;

    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }

    public static long PE() {
        findSquares();
        return smallestSum;
    }

    private static boolean check(long x, long y, long z) {
        if (Diophantine.root(x+y) < 0) return false;
        if (Diophantine.root(x-y) < 0) return false;
        if (Diophantine.root(x+z) < 0) return false;
        if (Diophantine.root(x-z) < 0) return false;
        if (Diophantine.root(y+z) < 0) return false;
        return Diophantine.root(y-z) >= 0;
    }

    private static void findSquaresHelper(int m, int n, int k) {
        long a = k * ((long) m * m - (long) n * n);
        long b = 2L * k*m*n;
        long c = k * ((long) m * m + (long) n * n);
        BigInteger bigA = BigInteger.valueOf(a);
        BigInteger bigB = BigInteger.valueOf(b);
        BigInteger bigC = BigInteger.valueOf(c);
        long[] cDivisors = Divisors.divisors(c);
        for (long k2 : cDivisors) {
            if (k2 > c/3) break;
            long cRem = c/k2;
            for (int i = 1; i <= 2; i++) {
                if (i == 2) {
                    if (cRem % 2 != 0) continue;
                    cRem /= 2;
                }
                long[] cRemDivisors = Divisors.divisors(cRem);
                long div1Limit = (long) Math.sqrt(cRem);
                for (long div1 : cRemDivisors) {
                    if (div1 >= div1Limit) break;
                    long div2 = cRem / div1;
                    long m2;
                    long n2;
                    if (i == 1) {
                        if (div1 % 2 != div2 % 2) continue;
                        m2 = (div1 + div2) / 2;
                        n2 = div2 - m2;
                    }
                    else {
                        n2 = div1;
                        m2 = div2;
                    }
                    BigInteger bigF = BigInteger.valueOf(k2).multiply(BigInteger.valueOf(m2).pow(2).add(BigInteger.valueOf(n2).pow(2)));
                    for (int j = 1; j <= 2; j++) {
                        BigInteger z2;
                        if (j == 1) z2 = bigF.pow(2).subtract(bigA.pow(2)).subtract(bigC.pow(2));
                        else z2 = bigF.pow(2).subtract(bigB.pow(2)).subtract(bigC.pow(2));
                        if (z2.compareTo(BigInteger.ZERO) < 0 || !z2.remainder(BigInteger.TWO).equals(BigInteger.ZERO)) continue;
                        BigInteger bigZ = z2.divide(BigInteger.TWO);
                        BigInteger xPlusZ = BigInteger.valueOf(c).pow(2).add(BigInteger.TWO.multiply(bigZ));
                        BigInteger sqrtRem = xPlusZ.sqrtAndRemainder()[1];
                        if (sqrtRem.compareTo(BigInteger.ZERO) > 0) continue;
                        try {
                            long z = bigZ.longValueExact();
                            long x = c*c + z;
                            long y = j == 1 ? (a*a + z) : (b*b + z);
                            long sum = x+y+z;
                            if (sum >= smallestSum) continue;
                            smallestSum = sum;
                            System.out.println("k m n z: " + k + " " + m + " " + n + " " + z);
                            System.out.println("x: " + x + ", y: " + y + ", z: " + z);
                            System.out.println("sum: " + sum);
                            if (!check(x, y, z)) throw new RuntimeException("Not valid");
                        } catch (ArithmeticException _) {}
                    }
                }
            }
        }
    }

    private static void findSquares() {
        for (int m = 1; (long) m * m*m*m < smallestSum; m++) {
            System.out.println("m: " + m);
            for (int n = 1; n < m; n++) {
                if (m % 2 == n % 2) continue;
                if (Diophantine.gcd(m, n) > 1) continue;
                long mm = (long) m * m;
                long nn = (long) n * n;
                long mnSum = mm*mm + nn*nn;
                long kLimit = (long) Math.sqrt((double) smallestSum / mnSum);
                for (int k = 1; k <= kLimit; k++) {
                    findSquaresHelper(m, n, k);
                }
            }
        }
    }
}