package euler;

public class PE_040 {
    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        int[] indexes = {1, 10, 100, 1_000, 10_000, 100_000, 1_000_000};
        return String.valueOf(productOfConstantDigits2(indexes));
    }

    private static long productOfConstantDigits2(int[] indexes) {
        long product = 1;
        for (int index : indexes) {
            product *= indexAt(index-1);
        }
        return product;
    }

    private static int indexAt(int index) {
        int powTen = 1;
        int factor = 1;
        int x = 9*powTen*factor;
        while (x <= index) {
            index -= x;
            powTen *= 10;
            factor++;
            x = 9*powTen*factor;
        }

        int num = powTen + index/factor;
        int i = factor - (index % factor) - 1;
        for (int j = 0; j < i; j++) {
            num /= 10;
        }

        return num % 10;
    }
}
