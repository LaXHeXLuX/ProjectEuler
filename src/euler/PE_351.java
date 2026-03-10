package euler;

public class PE_351 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 100_000_000;
        return String.valueOf(H(n));
    }

    private static long H(int order) {
        return 6*(order*(order+1L)/2 - totientSum(order));
    }

    private static long totientSum(int n) {
        if (n <= 0) return 0;
        int L = (int) Math.sqrt(n);
        long[] v = new long[L+1];
        long[] bigV = new long[n/L + 1];

        for (int x = 1; x <= L; x++) {
            long res = x*(x+1L)/2;
            int limit = (int) Math.sqrt(x);
            for (int g = 2; g <= limit; g++) {
                res -= v[x/g];
            }
            for (int z = 1; z <= limit; z++) {
                if (z != x/z) {
                    res -= (x/z - (x/(z+1))) * v[z];
                }
            }
            v[x] = res;
        }

        for (int x = n/L; x >= 1; x--) {
            int k = n/x;
            long res = k*(k+1L)/2;
            int limit = (int) Math.sqrt(k);
            for (int g = 2; g <= limit; g++) {
                if (k/g <= L) res -= v[k/g];
                else res -= bigV[x*g];
            }
            for (int z = 1; z <= limit; z++) {
                if (z != k/z) {
                    res -= (k/z - (k/(z+1))) * v[z];
                }
            }
            bigV[x] = res;
        }

        return bigV[1];
    }
}
