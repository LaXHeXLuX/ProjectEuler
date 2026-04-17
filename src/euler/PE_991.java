package euler;

import utils.Diophantine;

public class PE_991 {
    void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        int limit = 10_000_000;
        return String.valueOf(sumOfSolutions(limit));
    }

    private static long sumOfSolutions(long limit) {
        long sum = 0;

        for (long a = 1; a <= limit; a++) {
            long bLimit = limit - a;
            for (long b = 1; b <= bLimit; b++) {
                long sqrt = 16*b*b + 21*a*a - 36*a*b;
                if (sqrt < 0) {
                    System.out.println("sqrt: " + sqrt + ", a: " + a + ", b: " + b);
                }
                if (sqrt < 0) continue;
                long root = Diophantine.root(sqrt);
                if (root < 0) continue;
                long up = root - 2*b - 3*a;
                if (up <= 0 || up % 6 != 0) continue;
                long c = up / 6;
                if (a+b+c > limit) continue;
                sum += a+b+c;
                //System.out.println("found " + a + " " + b + " " + c);
            }
        }

        return sum;
    }
}
