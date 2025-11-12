package euler;

import utils.Diophantine;

public class PE_094 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        long limit = 1_000_000_000L;
        return perimeterSum(limit);
    }

    private static long perimeterSum(long perimeterLimit) {
        int n = 3;
        long[] pellBase = Diophantine.pell(n);
        long sum = 0;
        long[] nextPell = {pellBase[0]*pellBase[0] + n*pellBase[1]*pellBase[1], pellBase[0]*pellBase[1] + pellBase[1]*pellBase[0]};
        while (true) {
            long n1 = (nextPell[0] + 2) / 3;
            long n2 = (nextPell[0] - 2) / 3;
            long P1 = n1*6 - 2;
            long P2 = n2*6 + 2;
            if (P1 > perimeterLimit) break;
            if (nextPell[0] <= 2 || nextPell[0] % 3 == 0) continue;
            if (nextPell[0] % 3 == 1) {
                sum += P1;
            }
            if (P2 > perimeterLimit) break;
            if (nextPell[0] % 3 == 2) {
                sum += P2;
            }
            nextPell = new long[] {pellBase[0]*nextPell[0] + n*pellBase[1]*nextPell[1], pellBase[0]*nextPell[1] + pellBase[1]*nextPell[0]};
        }
        return sum;
    }
}
