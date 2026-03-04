package euler;

import utils.Combinations;

public class PE_015 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 20;
        int m = 20;
        return String.valueOf(Combinations.nChooseMBigInteger(m+n, n).longValue());
    }
}
