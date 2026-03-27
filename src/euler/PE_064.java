package euler;

public class PE_064 {
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
            int len = length(i);
            if (len % 2 == 1) counter++;
        }

        return counter;
    }

    private static int length(int n) {
        int a0 = (int) Math.sqrt(n);
        if (a0*a0 == n) return 0;
        int d1 = n - a0*a0;int a1 = 2*a0/d1;
        int m = a1*d1 - a0;
        int d = (n - m*m)/d1;
        int a = (a0 + m)/d;
        int len = 1;
        while (!(m == a0 && d == d1)) {
            len++;
            m = d*a - m;
            d = (n - m*m)/d;
            a = (a0 + m)/d;
        }

        return len;
    }
}

