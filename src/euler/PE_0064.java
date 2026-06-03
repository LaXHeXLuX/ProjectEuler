package euler;

public class PE_0064 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 10_000;
        return String.valueOf(countOdds(limit));
    }

    private static int countOdds(int limit) {
        int counter = 0;

        for (int i = 2; i <= limit; i++) {
            int len = continuedFractionLength(i);
            if (len % 2 == 1) counter++;
        }

        return counter;
    }

    private static int continuedFractionLength(int n) {
        int a0 = (int) Math.sqrt(n);
        if (a0*a0 == n) return 0;
        int a;
        int m = a0;
        int d = 1;
        int len = 0;
        do {
            len++;
            d = (n - m*m)/d;
            a = (a0 + m)/d;
            m = a*d - m;
        } while (!(m == a0 && d == 1));

        return len;
    }
}

