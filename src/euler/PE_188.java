package euler;

import utils.Diophantine;

public class PE_188 {
    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }

    public static long PE() {
        int digits = 8;
        long mod = Diophantine.pow(10, digits);
        long base = 1777;
        long exp = 1855;
        return Diophantine.tetrateMod(base, exp, mod);
    }
}
