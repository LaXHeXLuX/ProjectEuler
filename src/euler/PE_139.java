package euler;

import utils.Diophantine;

public class PE_139 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        long limit = 100_000_000;
        return amountOfTilingTriangles(limit);
    }

    private static long amountOfTilingTriangles(long perimeterLimit) {
        long count = 0;

        long mLimit = (long) Math.sqrt(perimeterLimit/2.0);
        long[] term = {1, 0};
        long m = 0;
        long n;
        while (m <= mLimit) {
            term = new long[] {term[0] + term[1]*2, term[0] + term[1]};
            m = term[0] + term[1];
            n = term[1];

            if (m % 2 == n % 2) continue;
            if (Diophantine.gcd(m, n) > 1) continue;
            long P = 2L*m*m + 2L*m*n;
            if (P >= perimeterLimit) break;
            count += (perimeterLimit-1) / P;
        }

        return count;
    }
}
