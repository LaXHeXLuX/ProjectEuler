package euler;

import utils.Diophantine;

public class PE_141 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        long limit = 1_000_000_000_000L;
        return progressiveSquares(limit);
    }

    private static void check(long x, long a, long b) {
        // mm / d = q, rem r
        long dNum = x*a*a;
        long dDen = b*b;
        if (dNum % dDen != 0) throw new RuntimeException("dNum doesn't divide dDen: " + dNum + " % " + dDen + " = " + (dNum % dDen));
        long d = dNum / dDen;
        long qNum = x*a;
        if (qNum % b != 0) throw new RuntimeException("qNun doesn't divide qDen: " + qNum + " % " + b + " = " + (qNum % b));
        long q = qNum / b;
        long mm = q*d + x;
        if (mm % d != x) throw new RuntimeException("x (" + x + ") isn't remainder: " + mm + " % " + q + " = " + (mm % q));
        long m = Diophantine.root(mm);
        if (m < 0) throw new RuntimeException("mm isn't square: " + mm);
    }

    private static long progressiveSquares(long limit) {
        long sum = 0;

        long aLimit = (long) Math.cbrt(limit);
        for (long a = 2; a <= aLimit - 20; a++) {
            long bLimit = limit / (a*a*a);
            if (bLimit > a-1) bLimit = a-1;
            int bStep = 1;
            if (a % 2 == 0) bStep = 2;
            for (long b = 1; b <= bLimit; b += bStep) {
                if (Diophantine.gcd(a, b) > 1) continue;
                long x0Limit = (long) Math.sqrt((double) limit / (a*a*a*b));
                for (long x0 = 1; x0 <= x0Limit; x0++) {
                    long mm = x0*x0*b*a*a*a + x0*b*b;
                    if (mm > limit) continue;
                    long m = Diophantine.root(mm);
                    if (m < 0) continue;

                    long x = b*b*x0;
                    check(x, a, b);
                    sum += m*m;
                }
            }
        }

        return sum;
    }
}
