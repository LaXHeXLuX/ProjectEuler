package euler;

import utils.Diophantine;

public class PE_141 {
    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        // 1) ss*bbb = x(x*aaa + bbb)
        long limit = 1000_000;
        return progressiveSquareSum(limit);
    }

    private static long progressiveSquareSum(long limit) {
        long sum = 0;

        long sqrtLimit = (long) Math.sqrt(limit-1);
        for (long s = 1; s <= sqrtLimit; s++) {
            long s2 = s*s;
            for (long a = 1; a < s; a++) {
                long a3 = a*a*a;
                long bMin = a3 / s2;
                if (bMin < 1) bMin = 1;
                for (long b = bMin; b < a; b++) {
                    if (Diophantine.gcd(a, b) > 1) continue;
                    long b3 = b*b*b;
                    // 1) sqrt = bbbb + 4*aaa*ss*b
                    long sqrt1 = b3*b + 4*a3*s2*b;
                    long root1 = Diophantine.root(sqrt1);
                    if (root1 > 0) {
                        long num1 = -b3 + b*root1;
                        long den1 = 2*a3;
                        if (num1 % den1 == 0) {
                            long x = num1/den1;
                            sum += s2;
                            System.out.println("1 x: " + x + ", a b: " + a + " / " + b + ". \tn: " + x*(x*a3 + b3)/(b3) + ", s: " + s);
                        }
                    }
                }
            }
        }
        return sum;
    }
}
