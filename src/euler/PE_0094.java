package euler;

import utils.Diophantine;

public class PE_0094 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long limit = 1_000_000_000L;
        return String.valueOf(perimeterSum2(limit));
    }

    private static long perimeterSum2(long L) {
        int D = 3;
        long[] base = Diophantine.pell(D);
        long sum = 0;

        long[] next = Diophantine.nextPell(base, base, D);
        while (2*next[0] + 6 <= L) {
            if (next[0] % 3 == 2) sum += 2*next[0] - 6;
            if (next[0] % 3 == 1) sum += 2*next[0] + 6;
            next = Diophantine.nextPell(base, next, D);
        }
        if (2*next[0] - 6 <= L && next[0] % 3 == 2) sum += 2*next[0] - 6;

        return sum;
    }
}
