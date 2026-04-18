package euler;

import utils.Diophantine;
import utils.Divisors;

public class PE_991 {
    void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        int limit = 10_000_000;
        return String.valueOf(sumOfSolutions2(limit));
    }

    private static long sumOfSolutions2(long limit) {
        long sum = 0;

        for (long a = 1; a <= limit; a++) {
            long bigA = 3*a*a;
            //long[] divisors = Divisors.divisors(bigA);
            long[] divisors = Divisors.divisorsFactor(a, 2, 3);
            for (long d1 : divisors) {
                if (d1 * d1 > bigA) break;
                long d2 = bigA / d1;
                if ((d1 + d2) % 4 != 0) continue;//throw new RuntimeException("d1 + d4: " + d1 + " + " + d2 + " % 4 != 0");
                long x = (d1 + d2) / 4;
                long y = d2 - 2*x;
                long[] bs = {9*a + y, 9*a - y};
                for (long b : bs) {
                    if (b <= 0) continue;//throw new RuntimeException("b neg: " + b);
                    if (b % 8 != 0) continue;//throw new RuntimeException("b: " + b + " % 8 != 0");
                    b /= 8;
                    long c = x - 2*b - 3*a;
                    if (c <= 0) continue;//throw new RuntimeException("c neg: " + c);
                    if (c % 6 != 0) continue;//throw new RuntimeException("c: " + c + " % 6 != 0");
                    c /= 6;
                    if (a+b+ c > limit) continue;//throw new RuntimeException("a+b+c: " + (a+b+c) + " > " + limit);
                    sum += a+b+ c;
                    //System.out.println("1: " + a + " " + b + " " + c);
                }
            }
        }

        return sum;
    }

    private static long sumOfSolutions(long limit) {
        long sum = 0;
        double SQRT3 = Math.sqrt(3);

        for (long a = 1; a <= limit; a++) {
            long bLimit = limit - a;
            long bLow = (long) (a * (2 - SQRT3));
            long bHigh = (long) (a * (2 + SQRT3));
            if (bLimit < bLow) bLow = bLimit;
            for (long b = 1; b <= bLow; b++) {
                long result = workingSum(limit, a, b);
                sum += result;
            }
            for (long b = bHigh + 1; b <= bLimit; b++) {
                long result = workingSum(limit, a, b);
                sum += result;
            }
        }

        return sum;
    }

    private static long sumOfSolutions0(long limit) {
        long sum = 0;
        for (long a = 1; a < limit; a++) {
            for (long b = 1; b < limit-a; b++) {
                for (long c = 1; c <= limit-a-b; c++) {
                    if (a+b+c > limit) throw new RuntimeException("bad sum: " + a + "+" + b + "+" + c + " = " + (a+b+c));
                    if (3*c*c + c*(2*b + 3*a) - (a*a + b*b - 4*a*b) == 0) {
                        sum += a+b+c;
                        System.out.println(a + " " + b + " " + c);
                    }
                }
            }
        }
        return sum;
    }

    private static long workingSum(long limit, long a, long b) {
        long sqrt = 16*b*b + 21*a*a - 36*a*b;
        if (sqrt < 0) return 0;
        long root = Diophantine.root(sqrt);
        if (root < 0) return 0;
        long up = root - 2*b - 3*a;
        if (up <= 0 || up % 6 != 0) return 0;
        long c = up / 6;
        if (a+b+c > limit) return 0;
        //System.out.println(a + " " + b + " " + c);
        return a+b+c;
    }
}
