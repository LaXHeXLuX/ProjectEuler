package euler;

import utils.Combinations;
import utils.Diophantine;

import java.math.BigInteger;

public class PE_020 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 100;
        BigInteger factorial = Combinations.factorialBigInteger(n);
        return Diophantine.digitSum(factorial);
    }
}
