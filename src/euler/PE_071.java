package euler;

import utils.Diophantine;

public class PE_071 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limitD = 1_000_000;
        int[] fraction = {3, 7};
        return String.valueOf(fractionBefore(limitD, fraction)[0]);
    }

    private static int[] fractionBefore(int limitD, int[] fraction) {
        int[] winningFraction = {-1, 1};
        long[] winningDiff = {1, 0};
        long N = fraction[0];
        long D = fraction[1];
        for (long d = limitD; d > 2; d--) {
            if (d % D == 0) continue;
            long n = (N*d)/D;
            if (Diophantine.gcd(n, d) > 1) continue;
            long diffN = N*d - n*D;
            if (diffN == 1) return new int[] {(int) n, (int) d};
            long diffD = D*d;
            if (diffN*winningDiff[1] < diffD*winningDiff[0]) {
                winningDiff[0] = diffN;
                winningDiff[1] = diffD;
                winningFraction[0] = (int) n;
                winningFraction[1] = (int) d;
            }
        }

        return winningFraction;
    }
}
