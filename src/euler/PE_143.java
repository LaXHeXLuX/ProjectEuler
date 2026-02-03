package euler;

import utils.Diophantine;
import utils.Primes;

import java.util.ArrayList;
import java.util.List;

public class PE_143 {
    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }
    public static long PE() {
        int limit = 1_000;
        return sumOfTorricelliTriangleSums(limit);
    }

    private static long sumOfTorricelliTriangleSums(long limit) {
        long limit2 = limit*limit;
        // limit2 > A + B + C
        long sum = 0;

        int aLimit = (int) (limit / Math.sqrt(3));
        for (int a = 1; a <= aLimit; a++) {
            boolean aPass = divisibleByPrime4kPlus1(a);
            long A = (long) a * a;
            int bLimit = (int) Math.sqrt(limit2 - A);
            for (int b = a; b <= bLimit; b++) {
                //if (!aPass && !divisibleByPrime4kPlus1(b)) continue;
                long B = (long) b * b;
                int cLimit = (int) Math.sqrt(a*a + b*b + 2*a*b);
                int cLimit2 = (int) Math.sqrt(limit2 - A - B);
                if (cLimit2 < cLimit) cLimit = cLimit2;
                for (int c = b; c < cLimit; c++) {
                    long C = (long) c * c;
                    long S = S(A, B, C);
                    if (S == -1 || S > limit2) continue;
                    if (!aPass && !divisibleByPrime4kPlus1(b)) {
                        System.out.println("works, but not. a: " + a + ", b: " + b + ", c: " + c + ", m*m = " + (-(A*A + B*B + C*C) + 2*(A*B + A*C + B*C)));
                    }
                    //System.out.println("a: " + a + ", b: " + b + ", c: " + c + ", S: " + S);
                    sum += S;
                }
            }
        }

        return sum;
    }

    private static long S(long A, long B, long C) {
        long threeTimesDD = 6*(A*B + A*C + B*C) - 3*(A*A + B*B + C*C);
        if (threeTimesDD < 0) return -1;
        long sqrt = Diophantine.root(threeTimesDD);
        if (sqrt < 0) return -1;
        long SS = A + B + C + sqrt;
        if (SS % 2 != 0) return -1;
        SS /= 2;
        long S = Diophantine.root(SS);
        if (S < 0) return -1;
        return S;
    }

    private static boolean divisibleByPrime4kPlus1(long n) {
        Primes.PF[] pfs = Primes.primeFactors(n);

        for (Primes.PF pf : pfs) {
            if (pf.primeFactor % 4 == 1) return true;
        }

        return false;
    }
}
