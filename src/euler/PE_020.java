package euler;

import utils.Combinations;
import utils.Diophantine;

import java.math.BigInteger;

public class PE_020 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 100;
        BigInteger factorial = Combinations.factorialBigInteger(n);
        return Diophantine.digitSum(factorial);
    }
}
