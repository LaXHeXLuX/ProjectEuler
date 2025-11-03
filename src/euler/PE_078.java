package euler;

public class PE_078 {
    private static long[] eulersFunction;

    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int n = 1_000_000;
        eulersFunction = new long[n+1];
        return firstToDivideN(n);
    }

    private static int firstToDivideN(int n) {
        int answer = 1;
        long ways = eulersFunction(answer, n);

        while (ways != 0) {
            answer++;
            ways = eulersFunction(answer, n);
        }

        return answer;
    }

    private static long eulersFunction(int n, int mod) {
        if (n < 0) return 0;
        if (n == 0) return 1;

        if (eulersFunction[n] > 0) return eulersFunction[n];

        int step = 1;
        long sum = 0;
        while (step <= n) {
            int nthStep = step * (3*step-1)/2;
            if (nthStep > n) break;
            int coefficient = (step % 2) * 2 - 1;
            sum += coefficient * eulersFunction(n-nthStep, mod);
            sum += coefficient * eulersFunction(n-nthStep-step, mod);
            sum = ((sum % mod) + mod) % mod;
            step++;
        }

        eulersFunction[n] = sum;

        return sum;
    }
}
