package euler;

public class PE_129 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000_000;
        return firstToExceed(limit);
    }

    private static int firstToExceed(int limit) {
        int n = limit - (limit-1) % 2;
        int leastK = leastKFor(n);
        while (leastK <= limit) {
            n+=2;
            if (n % 5 == 0) continue;
            leastK = leastKFor(n);
        }
        return n;
    }

    private static int leastKFor(int n) {
        int k = (int) Math.log10(n);
        int remainder = 1;
        while (remainder != 0) {
            k++;
            remainder = (remainder * 10 + 1) % n;
        }
        return k;
    }
}
