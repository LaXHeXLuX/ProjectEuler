package euler;

public class PE_112 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int[] fraction = {99, 100};
        return firstWithBouncyRatio(fraction[0], fraction[1]);
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
        int lastDigit = Math.toIntExact(n % 10);
        n /= 10;
        int digit = Math.toIntExact(n % 10);
        while (n > 0) {
            if (digit > lastDigit) {
                decreasing = false;
            }
            else if (digit < lastDigit) {
                increasing = false;
            }
            if (!increasing && !decreasing) return true;
            n /= 10;
            lastDigit = digit;
            digit = Math.toIntExact(n % 10);
        }
        return false;
    }
}
