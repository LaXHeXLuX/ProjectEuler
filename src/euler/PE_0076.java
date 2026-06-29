package euler;

public class PE_0076 {
    private static long[] p;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 100;

        p = new long[n];
        p[0] = 1;

        for (int i = 1; i < n; i++) {
            p[i] = p(i);
        }

        return String.valueOf(p(n) - 1);
    }

    private static int g(int k) {
        return k*(3*k-1)/2;
    }

    private static long p(int n) {
        long sum = 0;

        int sign = 1;
        int k = 1;
        int gk = g(k);
        while (n >= gk) {
            sum += sign * p[n - gk];

            k = -k;
            if (k > 0) {
                k++;
                sign = -sign;
            }
            gk = g(k);
        }

        return sum;
    }
}
