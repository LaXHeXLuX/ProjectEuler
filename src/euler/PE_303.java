package euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PE_303 {

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 10_000;
        return String.valueOf(sum(limit));
    }

    private static long sum(int limit) {
        long sum = 0;
        for (int i = 1; i <= limit; i++) {
            sum += f(i);
        }
        return sum;
    }

    private static long f(int n) {
        if (onlyDigitsBelow2(n)) return 1;
        Set<Long> seenResidues = new HashSet<>();
        long ten = 1;
        List<Long> multipliers = List.of(0L);

        while (true) {
            List<Long> nextMultipliers = new ArrayList<>();
            int iStart = 0;
            if (ten == 1) iStart = 1;
            for (int i = iStart; i <= 9; i++) {
                for (Long multiplier : multipliers) {
                    long m = ten*i + multiplier;
                    long prod = (m * n) / ten;
                    if (prod < 0) {
                        prod = BigInteger.valueOf(m).multiply(BigInteger.valueOf(n)).divide(BigInteger.valueOf(ten)).longValueExact();
                    }
                    if (prod % 10 <= 2) {
                        long residue = prod / 10;
                        if (onlyDigitsBelow2(residue)) return m;
                        if (seenResidues.add(residue)) nextMultipliers.add(m);
                    }
                }
            }
            multipliers = nextMultipliers;
            ten *= 10;
        }
    }

    private static boolean onlyDigitsBelow2(long n) {
        while (n > 0) {
            if (n % 10 > 2) return false;
            n /= 10;
        }
        return true;
    }
}
