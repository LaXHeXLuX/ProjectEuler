package euler;

import utils.Diophantine;

public class PE_188 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int digits = 8;
        long mod = Diophantine.pow(10, digits);
        long base = 1777;
        long exp = 1855;
        return Diophantine.tetrateMod(base, exp, mod);
    }
}
