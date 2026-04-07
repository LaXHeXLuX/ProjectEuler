package euler.other;

import utils.Diophantine;

import java.util.Arrays;

public class random_test {
    void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        test();
        return null;
    }

    private static void test() {
        int n = 1726;

        int[] cf = Diophantine.continuedFraction(n);

        System.out.println(Arrays.toString(cf));
    }
}
