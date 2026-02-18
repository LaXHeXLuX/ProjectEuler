package euler;

public class PE_303 {
    private static long smallestSolution;
    private static long[] moduloProducts;

    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e - s) + " ms");
    }

    public static long PE() {
        int limit = 1_000;
        return sum(limit);
    }

    private static long sum(long limit) {
        long sum = 0;
        for (int i = 1; i <= limit; i++) {
            System.out.print(i + "\r");
            sum += f(i);
        }
        return sum;
    }

    private static long f(int n) {
        if (n < 3) return 1;
        smallestSolution = Long.MAX_VALUE/10;
        moduloProducts = new long[n];
        f(n, 1, 0);
        f(n, 2, 0);
        long result = smallestSolution;
        smallestSolution = -1;
        return result;
    }

    private static void f(int n, int x, long p) {
        if (moduloProducts[x] > 0 && moduloProducts[x] < p) return;
        moduloProducts[x] = p;
        if (p >= smallestSolution) return;
        if (x == 0) {
            //System.out.println("new smallest: " + p);
            smallestSolution = p;
            return;
        }
        //System.out.println("f(" + n + ", " + x + ", " + p + ")");
        for (int i = 2; i >= 0; i--) {
            int newX = 10*x + i;
            f(n, newX % n, p*10 + newX/n);
        }
    }

    private static boolean onlyDigitsBelow2(long n) {
        while (n > 0) {
            if (n % 10 > 2) return false;
            n /= 10;
        }
        return true;
    }
}
