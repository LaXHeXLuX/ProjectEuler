package euler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PE_303 {

    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }

    public static long PE() {
        int limit = 10_000;
        return sum(limit);
    }

    private static long sum(int limit) {
        long sum = 0;
        for (int i = 1; i <= limit; i++) {
            sum += f(i);
        }
        return sum;
    }

    private static long allNinesSolution(int n) {
        int count = 0;
        while (n > 0) {
            count++;
            if (n % 10 != 9) return -1;
            n /= 10;
        }
        long result = 0;
        for (int i = 1; i <= 5; i+=2) {
            for (int j = 0; j < count; j++) {
                result = 10*result + i;
            }
        }
        for (int i = 0; i < count-1; i++) {
            result = 10*result + 7;
        }
        return 10*result + 8;
    }

    private static long f(int n) {
        if (onlyDigitsBelow2(n)) return 1;
        long allNines = allNinesSolution(n); // help here verify
        if (allNines > 0) return allNines;
        Set<Integer> seenResidues = new HashSet<>();
        long ten = 1;
        List<Long> multipliers = List.of(0L);

        while (true) {
            List<Long> nextMultipliers = new ArrayList<>();
            int iStart = 0;
            if (ten == 1) iStart = 1;
            for (int i = iStart; i <= 9; i++) {
                for (Long multiplier : multipliers) {
                    long m = ten*i + multiplier;
                    int prod = Math.toIntExact((m * n) / ten);
                    if (prod % 10 <= 2) {
                        int residue = prod / 10;
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
