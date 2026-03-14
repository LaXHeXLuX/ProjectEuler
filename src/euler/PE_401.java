package euler;

public class PE_401 {
    void main() {
        System.out.println(PE());
    }

    public static String PE() {
        long mod = 1_000_000_000L;
        long n = 1_000_000_000_000_000L;
        return String.valueOf(SIGMA2(n, mod));
    }

    private static long sumOfSquares(long n, long mod) {
        long n0 = n;
        long n1 = n+1;
        long n2 = 2*n+1;
        if (n % 2 == 0) n0 /= 2;
        else n1 /= 2;
        if (n % 3 == 0) n0 /= 3;
        else if (n % 3 == 2) n1 /= 3;
        else n2 /= 3;
        n0 = n0 % mod;
        n1 = n1 % mod;
        n2 = n2 % mod;
        return (((n0*n1) % mod) * n2) % mod;
    }

    public static long SIGMA2(long n, long mod) {
        long sum = n % mod;
        long r;
        long prevSum = 0;
        for (long l = 2; l <= n; l = r + 1) {
            long value = n / l;
            r = n / value;
            long newSum = sumOfSquares(r, mod);
            long rangeSum = (newSum - prevSum + mod) % mod;
            prevSum = newSum;
            sum = (sum + (rangeSum * (value % mod)) % mod) % mod;
        }
        return sum;
    }
}
