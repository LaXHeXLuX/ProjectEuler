package euler;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class PE_029 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 100;
        Set<BigInteger> powers = distinctPowers(limit, limit);
        return powers.size();
    }

    private static Set<BigInteger> distinctPowers(int limitA, int limitB) {
        Set<BigInteger> powers = new HashSet<>();
        for (int a = 2; a <= limitA; a++) {
            for (int b = 2; b <= limitB; b++) {
                powers.add(power(a, b));
            }
        }
        return powers;
    }

    private static BigInteger power(int a, int b) {
        BigInteger power = BigInteger.valueOf(a);
        for (int i = 0; i < b-1; i++) {
            power = power.multiply(BigInteger.valueOf(a));
        }
        return power;
    }
}
