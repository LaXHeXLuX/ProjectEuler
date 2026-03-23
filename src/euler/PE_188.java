package euler;

import utils.Diophantine;

public class PE_188 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int digits = 8;
        long mod = Diophantine.pow10[digits];
        long base = 1777;
        long exp = 1855;
        return String.valueOf(Diophantine.tetrateMod(base, exp, mod));
    }
}
