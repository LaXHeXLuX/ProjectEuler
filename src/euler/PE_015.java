package euler;

import utils.Combinations;

public class PE_015 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 20;
        int m = 20;
        return Combinations.nChooseMBigInteger(m+n, n).longValue();
    }
}
