package euler;

import utils.Diophantine;
import utils.Primes;

import java.math.BigInteger;

public class PE_141 {
    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        // 1) ss*bbb = x(x*aaa + bbb)
        long limit = 1_000_000_000;
        return progressiveSquares(limit);
    }

    private static long progressiveSquares(long limit) {
        long sum = 0;

        int aLimit = (int) Math.cbrt(limit);
        for (int a = 2; a <= aLimit; a++) {
            int bLimit = Math.toIntExact(limit / ((long) a * a*a));
            if (bLimit >= a) bLimit = a-1;
            for (int b = 1; b <= bLimit; b++) {
                if (Diophantine.gcd(a, b) > 1) continue;
                int mLimit = (int) Math.sqrt(limit);
                int modB = b;
                if (modB % 2 == 0) {
                    modB /= 2;
                    if (modB % 2 == 0) modB /= 2;
                }
                Primes.PF[] pfs = Primes.primeFactors(modB);
                modB = 1;
                for (Primes.PF pf : pfs) modB *= (int) Diophantine.pow(pf.primeFactor, (pf.power + 1) / 2);
                for (int m = modB; m <= mLimit; m += modB) {
                    BigInteger bigB = BigInteger.valueOf(b);
                    BigInteger square = bigB.pow(4).add(BigInteger.valueOf(4).multiply(BigInteger.valueOf(a).pow(3)).multiply(bigB).multiply(BigInteger.valueOf(m).pow(2)));
                    BigInteger[] sqrtAndRem = square.sqrtAndRemainder();
                    if (!sqrtAndRem[1].equals(BigInteger.ZERO)) continue;
                    long sqrt = sqrtAndRem[0].longValueExact();
                    long x0Times2baaa = (long) -b * b + sqrt;
                    if (x0Times2baaa % (2L*b*a*a*a) != 0) continue;
                    int x0 = Math.toIntExact(x0Times2baaa / (2L*b*a*a*a));
                    int x = x0 * b*b;
                    sum += (long) m * m;
                    System.out.println("1 x: " + x + ", a b: " + a + " / " + b + ". \tn: " + m*m + ", m: " + m);
                }
            }
        }

        return sum;
    }
}
