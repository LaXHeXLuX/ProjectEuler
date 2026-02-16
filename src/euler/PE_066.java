package euler;

import utils.Diophantine;

import java.math.BigInteger;

public class PE_066 {

    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1000;
        return maxMinimalSolution(limit);
    }

    private static int maxMinimalSolution(int limit) {
        BigInteger max = BigInteger.ZERO;
        int maxD = -1;

        for (int D = 1; D <= limit; D++) {
            BigInteger[] pell = Diophantine.pellBig(D);
            if (pell.length == 0) continue;
            if (pell[0].compareTo(max) > 0) {
                max = pell[0];
                maxD = D;
            }
        }

        return maxD;
    }
}
