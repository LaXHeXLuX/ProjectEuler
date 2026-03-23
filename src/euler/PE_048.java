package euler;

import utils.Diophantine;

public class PE_048 {

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 1000;
        long mod = Diophantine.pow10[10];
        return String.valueOf(selfPowersSum(limit, mod));
    }

    private static long selfPowersSum(int limit, long mod) {
        long sum = 0;
        for (int i = 1; i <= limit; i++) {
            sum = (sum + powModExact(i, i, mod)) % mod;
        }
        return sum;
    }

    private static long powModExact(long base, int exp, long mod) {
        long result = 1;
        base %= mod;

        while (exp > 0) {
            if ((exp & 1) == 1) result = Diophantine.mulModExact(result, base, mod);
            base = Diophantine.mulModExact(base, base, mod);
            exp >>= 1;
        }

        return result;
    }
}
