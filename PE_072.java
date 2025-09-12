import util.Primes;

public class PE_072 {
    private static int[] totients;

    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int limit = 1_000_000;
        totients = totients(limit+1);
        return elementCountForAllFractions(limit);
    }

    private static long elementCountForAllFractions(int limitD) {
        long answer = 0;
        for (int i = 2; i <= limitD; i++) {
            answer += totients[i];
        }
        return answer;
    }

    public static int[] totients(int n) {
        int[] phi = new int[n];

        for (int i = 0; i < n; i++) {
            phi[i] = i;
        }

        for (int p = 2; p < n; p++) {
            if (phi[p] == p) {
                for (int k = p; k < n; k += p) {
                    phi[k] -= phi[k] / p;
                }
            }
        }

        return phi;
    }
}
