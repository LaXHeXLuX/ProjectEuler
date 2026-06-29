package euler;

public class PE_0078 {
    private static long[] p;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 1_000_000;
        p = new long[n+1];
        return String.valueOf(firstToDivideN(n));
    }

    private static int firstToDivideN(int n) {
        int pSize = 100;
        while (true) {
            p = new long[pSize];

            p[0] = 1;
            int answer = 1;

            while (answer < p.length) {
                p[answer] = p(answer, n);
                if (p[answer] == 0) return answer;
                answer++;
            }

            pSize *= 10;
        }
    }

    private static int g(int k) {
        return k*(3*k-1)/2;
    }

    private static long p(int n, int mod) {
        long sum = 0;

        int sign = 1;
        int k = 1;
        int gk = g(k);
        while (n >= gk) {
            sum = (sum + sign * p[n - gk]) % mod;

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
