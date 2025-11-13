package euler;

import utils.Combinations;

import java.math.BigInteger;

public class PE_106 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 12;
        return countOfSubsetPairs(n);
    }

    private static long countOfSubsetPairs(int n) {
        long sum = 0;
        for (int i = 2; i <= n/2; i++) {
            BigInteger allPairs = Combinations.nChooseMBigInteger(n, i).multiply(Combinations.nChooseMBigInteger(n-i, i)).divide(BigInteger.TWO);
            BigInteger sub = Combinations.nChooseMBigInteger(n, 2*i).multiply(Combinations.catalan(i));
            sum += allPairs.subtract(sub).longValue();
        }
        return sum;
    }
}
