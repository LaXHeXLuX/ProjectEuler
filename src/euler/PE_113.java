package euler;

import utils.Combinations;

public class PE_113 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int digits = 100;
        return String.valueOf(nonBouncyCount(digits));
    }

    private static long nonBouncyCount(int digits) {
        long increasing = count(digits, true);
        long decreasing = count(digits, false);
        long same = 9L * digits;
        return increasing + decreasing + same;
    }

    private static long count(int digits, boolean increasing) {
        if (digits == 1) return 0;
        long sum = count(digits-1, increasing);
        int m = digits-2;
        int extra = increasing ? 0 : 1;
        for (int n = 1; n < 9 + extra; n++) {
            sum += (9 - n + extra) * Combinations.nChooseM(n+m, m);
        }
        return sum;
    }
}
