package euler;

public class PE_055 {
    private static final int iterationLimit = 50;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 10_000;
        return String.valueOf(lychrelNumbersUnder(limit));
    }

    private static int lychrelNumbersUnder(int limit) {
        int count = 0;

        for (int i = 5; i < limit; i++) {
            if (lychrel(i)) count++;
        }

        return count;
    }

    private static boolean lychrel(long n) {
        n += reverse(n);
        long r = reverse(n);
        int counter = 1;

        while (r != n) {
            n += r;
            r = reverse(n);
            if (n < 0) return true;
            if (++counter > iterationLimit) return true;
        }
        return false;
    }

    private static long reverse(long n) {
        long r = 0;
        while (n > 0) {
            r = 10*r + n%10;
            n /= 10;
        }
        return r;
    }
}
