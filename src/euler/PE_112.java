package euler;

public class PE_112 {
    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        int[] fraction = {99, 100};
        return String.valueOf(firstWithBouncyRatio(fraction[0], fraction[1]));
    }

    private static long firstWithBouncyRatio(int num, int den) {
        int bouncyNum = 0;
        int n = 1;
        while (bouncyNum*den != n*num) {
            n++;
            if (numberIsBouncy(n)) bouncyNum++;
        }
        return n;
    }

    private static boolean numberIsBouncy(long n) {
        boolean increasing = true;
        boolean decreasing = true;
        int lastDigit = (int) (n % 10);
        while ((n /= 10) > 0) {
            int digit = (int) (n % 10);
            if (digit > lastDigit) decreasing = false;
            else if (digit < lastDigit) increasing = false;
            if (!increasing && !decreasing) return true;
            lastDigit = digit;
        }
        return false;
    }
}
