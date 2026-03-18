package euler;

import utils.Diophantine;

import java.math.BigInteger;

public class PE_016 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 2;
        int p = 1000;
        BigInteger power = BigInteger.valueOf(n).pow(p);
        return String.valueOf(Diophantine.digitSum(power));
    }
}
