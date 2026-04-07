package euler;

import java.math.BigInteger;

public class PE_097 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long factor = 28433;
        long base = 2;
        long exp = 7830457;
        long mod = 10_000_000_000L;
        long result = BigInteger.valueOf(base).modPow(BigInteger.valueOf(exp), BigInteger.valueOf(mod)).longValueExact();
        return String.valueOf((factor * result + 1) % mod);
    }
}
