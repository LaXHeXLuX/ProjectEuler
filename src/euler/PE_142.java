package euler;

import utils.Diophantine;
import utils.Divisors;

public class PE_142 {
    private static long smallestSum = Long.MAX_VALUE;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        findSquares();
        return smallestSum;
    }

    private static void findSquaresHelper(long c) {
        long cc = c*c;
        long[] squareDivisors = Divisors.divisors(c, 2);
        for (int i = 0; i < (squareDivisors.length+1) / 2; i++) {
            long d1 = squareDivisors[i];
            long d2 = cc/d1;
            if ((d1+d2) % 2 != 0) continue;
            long f = (d1+d2) / 2;
            long d = d2 - f;
            long eStart = (long) Math.sqrt(f*f - d*d) + 1;
            if (eStart <= d) eStart = d+1;
            if (eStart % 2 != (f + d) % 2) eStart++;
            for (long e = eStart; e < f; e += 2) {
                long y = (f*f + d*d - e*e) / 2;
                long x = (f*f - d*d + e*e) / 2;
                long z = x - cc;
                long sum = x+y+z;
                if (sum > smallestSum) break;
                long aa = y - z;
                long bb = x - y;
                long a = Diophantine.root(aa);
                long b = Diophantine.root(bb);
                if (a < 0 || b < 0) continue;
                smallestSum = sum;
            }
        }
    }

    private static void findSquares() {
        for (int m = 1; (long) m * m*m*m < smallestSum; m++) {
            for (int n = 1; n < m; n++) {
                if (m % 2 == n % 2) continue;
                if (Diophantine.gcd(m, n) > 1) continue;
                long mm = (long) m * m;
                long nn = (long) n * n;
                long mnSum = mm*mm + nn*nn;
                for (int k = 1; k <= Math.sqrt((double) smallestSum / mnSum); k++) {
                    long c = k * ((long) m * m + (long) n * n);
                    findSquaresHelper(c);
                }
            }
        }
    }
}