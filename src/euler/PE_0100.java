package euler;

import utils.Diophantine;

import java.util.List;

public class PE_0100 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long L = 1_000_000_000_000L;
        return String.valueOf(blueDiscCount(L));
    }

    private static long blueDiscCount(long L) {
        int D = 2;
        long[] base = Diophantine.pell(D);
        List<long[]> bases = Diophantine.pell(D, 2, true);
        if (bases.size() > 1) throw new RuntimeException("I don't want to deal with multiple bases");
        long[] next = bases.getFirst();

        long mLimit = 2*L - 1;
        while (next[1] <= mLimit) {
            next = Diophantine.nextPell(base, next, D);
        }

        return (next[0] + 2) / 4;
    }
}
